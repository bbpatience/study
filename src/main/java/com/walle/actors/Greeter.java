package com.walle.actors;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * @author: bbpatience
 * @date: 2018/12/17
 * @description: Greeter
 **/
public class Greeter extends UntypedActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public static enum Msg {
        GREET, DONE
    }

    @Override
    public void onReceive(Object msg) {
        if (msg == Msg.GREET) {
            log.info("{} receive", getSelf().path());
            System.out.println("Hello World!");
            getSender().tell(Msg.DONE, getSelf());
        } else
            unhandled(msg);
    }
}
