package com.walle.concurrent.producer.disruptor;

import com.lmax.disruptor.WorkHandler;

/**
 * @author: bbpatience
 * @date: 2018/12/12
 * @description: Consumer
 **/
public class Consumer implements WorkHandler<PCData> {

    @Override
    public void onEvent(PCData pcData) throws Exception {
        System.out.println(Thread.currentThread().getId() + " event: --" +
            pcData.getValue() * pcData.getValue() + " --");
    }
}
