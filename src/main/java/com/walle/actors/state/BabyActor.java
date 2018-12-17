package com.walle.actors.state;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;

/**
 * @author: bbpatience
 * @date: 2018/12/17
 * @description: BabyActor
 **/
public class BabyActor extends UntypedActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public static enum Msg {
        SLEEP, PLAY, CLOSE
    }

    Procedure<Object> angry = new Procedure<Object>() {
        @Override
        public void apply(Object param) throws Exception {
            System.out.println("Angry apply: " + param);
            if (param == Msg.SLEEP) {
                getSender().tell("I'm already angry.", getSelf());
                System.out.println("I am already angry.");
            } else if (param == Msg.PLAY) {
                System.out.println("I like playing.");
                getContext().become(happy);
            }
        }
    };

    Procedure<Object> happy = (param) -> {
            System.out.println("Happy apply: " + param);
            if (param == Msg.PLAY) {
                getSender().tell("I'm already happy.", getSelf());
                System.out.println("I am already happy.");
            } else if (param == Msg.SLEEP) {
                System.out.println("I don't want to sleep.");
                getContext().become(angry);
            }
    };

    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println("on receive " + message);
        if (message == Msg.SLEEP) {
            getContext().become(angry);
        } else if (message == Msg.PLAY) {
            getContext().become(happy);
        } else {
            unhandled(message);
        }
    }
}
