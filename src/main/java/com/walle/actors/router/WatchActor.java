package com.walle.actors.router;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;
import com.walle.actors.inbox.MyWorker;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: bbpatience
 * @date: 2018/12/24
 * @description: WatchActor
 **/
public class WatchActor extends UntypedActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public Router router;
    {
        List<Routee> routees = new ArrayList<>();
        for (int i =0;i < 5 ; i++) {
            ActorRef worker = getContext().actorOf(Props.create(MyWorker.class), "worker_" + i);
            getContext().watch(worker);
            routees.add(new ActorRefRoutee(worker));
        }
        router = new Router(new RoundRobinRoutingLogic(), routees);
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof MyWorker.Msg) {
            router.route(message, getSender());
        } else if (message instanceof Terminated) {
            router = router.removeRoutee(((Terminated)message).actor());
            System.out.println(((Terminated)message).actor().path() + " is closed , router = " + router.routees().size());
            if (router.routees().size() == 0) {
                System.out.println("Close System");
                RouterMain.flag.send(false);
                getContext().system().shutdown();
            }
        } else {
            unhandled(message);
        }
    }
}
