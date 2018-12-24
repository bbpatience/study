package com.walle.actors.router;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.agent.Agent;
import akka.dispatch.ExecutionContexts;
import com.typesafe.config.ConfigFactory;
import com.walle.actors.inbox.MyWorker.Msg;

/**
 * @author: bbpatience
 * @date: 2018/12/24
 * @description: RouterMain
 **/
public class RouterMain {
    public static Agent<Boolean> flag = Agent.create(true, ExecutionContexts.global());

    public static void main(String[] args) throws InterruptedException {
        ActorSystem system = ActorSystem.create("route", ConfigFactory.load("application.conf"));
        ActorRef w = system.actorOf(Props.create(WatchActor.class), "watcher");

        int i = 1;
        while (flag.get()) {
            w.tell(Msg.WORKING, ActorRef.noSender());
            if (i % 10 == 0) {
                w.tell(Msg.CLOSE, ActorRef.noSender());
            }
            i++;
            Thread.sleep(100);
        }
    }
}
