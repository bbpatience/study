package com.walle.actors.agent;

import akka.actor.UntypedActor;
import akka.dispatch.Mapper;
import scala.concurrent.Future;

/**
 * @author: bbpatience
 * @date: 2018/12/24
 * @description: CounterActor
 **/
public class CounterActor extends UntypedActor {

    Mapper addMapper = new Mapper<Integer, Integer>() {
        @Override
        public Integer apply(Integer parameter) {
            return parameter+1;
        }
    };
    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof  Integer) {
            for (int i=0;i< 10000;i++) {
                Future<Integer> f = AgentDemo.counterAgent.alter(addMapper);
                AgentDemo.futures.add(f);
            }
            getContext().stop(getSelf());
        } else {
            unhandled(message);
        }
    }
}
