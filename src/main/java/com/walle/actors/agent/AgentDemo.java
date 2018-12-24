package com.walle.actors.agent;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.agent.Agent;
import akka.dispatch.ExecutionContexts;
import akka.dispatch.Futures;
import akka.dispatch.OnComplete;
import com.typesafe.config.ConfigFactory;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

/**
 * @author: bbpatience
 * @date: 2018/12/24
 * @description: AgentDemo
 **/
public class AgentDemo {
    public static  Agent<Integer> counterAgent = Agent.create(0, ExecutionContexts.global());

    public static ConcurrentLinkedQueue<Future<Integer>> futures = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) throws InterruptedException , TimeoutException {
        ActorSystem system = ActorSystem.create("agentdemo", ConfigFactory.load("application.conf"));

        ActorRef[] counter = new ActorRef[10];

        for (int i = 0; i < counter.length; i++) {
            counter[i] = system.actorOf(Props.create(CounterActor.class), "counter_" + i);
        }
        final Inbox inbox = Inbox.create(system);
        for (int i = 0; i < counter.length; i++) {
            inbox.send(counter[i], 1);
            inbox.watch(counter[i]);
        }

        int closeCount = 0;

        while (true) {
            Object msg = inbox.receive(Duration.create(1, TimeUnit.SECONDS));
            if (msg instanceof Terminated) {
                closeCount++;
                if (closeCount == counter.length)
                    break;

            } else {
                System.out.println(msg);
            }
        }

        Futures.sequence(futures, system.dispatcher()).onComplete(
            new OnComplete<Iterable<Integer>>() {
                @Override
                public void onComplete(Throwable failure, Iterable<Integer> success)
                    throws Throwable {
                    System.out.println("counterAgent=" + counterAgent.get());
                    system.shutdown();
                }

            }, system.dispatcher());
    }
}
