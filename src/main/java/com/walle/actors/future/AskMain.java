package com.walle.actors.future;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;
import com.walle.actors.helloworld.HelloMainSimple.Terminator;
import com.walle.actors.inbox.MyWorker;
import java.util.concurrent.TimeUnit;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import static akka.pattern.Patterns.ask;
import static akka.pattern.Patterns.pipe;

/**
 * @author: bbpatience
 * @date: 2018/12/17
 * @description: AskMain
 **/
public class AskMain {

    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("askdemo", ConfigFactory.load("application.conf"));
        ActorRef worker = system.actorOf(Props.create(MyWorker.class), "worker");
        ActorRef printer = system.actorOf(Props.create(PrintActor.class), "printer");

        system.actorOf(Props.create(Terminator.class, worker), "Terminator");

        Future<Object> f = ask(worker, 5, 1500);
        int re = (int) Await.result(f, Duration.create(6, TimeUnit.SECONDS));

        System.out.println("return:" + re);

        f = ask(worker, 6, 1500);
        pipe(f, system.dispatcher()).to(printer);

        worker.tell(PoisonPill.getInstance(), ActorRef.noSender());

    }
}
