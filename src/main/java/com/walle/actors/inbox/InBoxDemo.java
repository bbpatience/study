package com.walle.actors.inbox;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import akka.actor.Terminated;
import com.typesafe.config.ConfigFactory;
import com.walle.actors.inbox.MyWorker.Msg;
import java.util.concurrent.TimeUnit;
import scala.concurrent.duration.Duration;

/**
 * @author: bbpatience
 * @date: 2018/12/17
 * @description: InBoxDemo
 **/
public class InBoxDemo {

    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("inboxdemo", ConfigFactory.load("application.conf"));
        ActorRef worker = system.actorOf(Props.create(MyWorker.class), "worker");

        final Inbox inbox = Inbox.create(system);
        inbox.watch(worker);
        inbox.send(worker, Msg.WORKING);
        inbox.send(worker, Msg.DONE);
        inbox.send(worker, Msg.CLOSE);

        while (true) {
            Object msg = inbox.receive(Duration.create(1, TimeUnit.SECONDS));
            if (msg == Msg.CLOSE) {
                System.out.println("My worker is closing.");
            } else if (msg instanceof Terminated) {
                System.out.println("My worker is dead.");
                system.shutdown();
                break;
            } else {
                System.out.println(msg);
            }
        }
    }
}
