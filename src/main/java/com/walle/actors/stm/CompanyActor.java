package com.walle.actors.stm;

import akka.actor.UntypedActor;
import akka.transactor.Coordinated;
import scala.concurrent.stm.Ref;
import scala.concurrent.stm.japi.STM;

/**
 * @author: bbpatience
 * @date: 2018/12/24
 * @description: CompanyActor
 **/
public class CompanyActor extends UntypedActor {

    private Ref.View<Integer> count = STM.newRef(100);

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Coordinated) {
            final Coordinated c = (Coordinated) message;
            final int downCount = (Integer) c.getMessage();

            STMDemo.employee.tell(c.coordinate(downCount), getSelf());

            try {
                c.atomic(new Runnable() {
                    @Override
                    public void run() {
                        if (count.get() < downCount) {
                            throw new RuntimeException("less than " + downCount);
                        }
                        STM.increment(count, -downCount);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("GetCount".equals(message)) {
            getSender().tell(count.get(), getSelf());
        } else {
            unhandled(message);
        }
    }
}
