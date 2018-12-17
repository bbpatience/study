package com.walle.actors.inbox;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * @author: bbpatience
 * @date: 2018/12/17
 * @description: MyWorker
 **/
public class MyWorker  extends UntypedActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public static enum Msg {
        WORKING, DONE, CLOSE
    }
    @Override
    public void onReceive(Object message) throws Throwable {
        if (message == Msg.WORKING) {
            log.info("I'm working.");
        } else if (message == Msg.DONE) {
            log.info("Stop working.");
        } else if (message == Msg.CLOSE) {
            log.info("I will shutdown.");
            getSender().tell(Msg.CLOSE, getSelf());
            getContext().stop(getSelf());
        } else {
            unhandled(message);
        }
    }
}
