package com.walle.actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.typesafe.config.ConfigFactory;

/**
 * @author: bbpatience
 * @date: 2018/12/17
 * @description: HelloMainSimple
 **/
public class HelloMainSimple {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("Hello");
        ActorRef a = system.actorOf(Props.create(HelloWorld.class), "helloWorld");
        system.actorOf(Props.create(Terminator.class, a), "terminator");
    }

    public static class Terminator extends UntypedActor {

        private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);
        private final ActorRef ref;

        public Terminator(ActorRef ref) {
            this.ref = ref;
            getContext().watch(ref);
        }

        @Override
        public void onReceive(Object msg) {
            if (msg instanceof Terminated) {
                log.info("{} has terminated, shutting down system", ref.path());
                getContext().system().terminate();
            } else {
                unhandled(msg);
            }
        }

    }
}
