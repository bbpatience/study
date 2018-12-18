package com.walle.actors.future;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.walle.actors.inbox.MyWorker.Msg;
import akka.transactor.Coordinated;
import scala.concurrent.stm.japi.STM;

/**
 * @author: bbpatience
 * @date: 2018/12/17
 * @description: PrintActor
 **/
public class PrintActor extends UntypedActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Integer) {
            System.out.println("Printer: " + message);
        }
        if (message == Msg.DONE) {
            log.info("Stop working");
        }
        if (message == Msg.CLOSE) {
            log.info("I will shutdown.");
            getSender().tell(Msg.CLOSE, getSelf());
            getContext().stop(getSelf());
        } else {
            unhandled(message);
        }
    }
}
