package com.walle.actors.state;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;
import com.walle.actors.helloworld.HelloMainSimple.Terminator;
import com.walle.actors.state.BabyActor.Msg;

/**
 * @author: bbpatience
 * @date: 2018/12/17
 * @description: StateDemo
 **/
public class StateDemo {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("become", ConfigFactory.load("application.conf"));
        ActorRef child = system.actorOf(Props.create(BabyActor.class), "BabyState");

        system.actorOf(Props.create(Terminator.class, child), "Terminator");
        child.tell(Msg.PLAY, ActorRef.noSender());
        child.tell(Msg.SLEEP, ActorRef.noSender());
        child.tell(Msg.PLAY, ActorRef.noSender());
        child.tell(Msg.PLAY, ActorRef.noSender());
        child.tell(PoisonPill.getInstance(), ActorRef.noSender());
    }
}
