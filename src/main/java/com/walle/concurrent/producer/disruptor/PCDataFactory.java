package com.walle.concurrent.producer.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author: bbpatience
 * @date: 2018/12/12
 * @description: PCDataFactory
 **/
public class PCDataFactory implements EventFactory<PCData> {

    public PCData newInstance() {
        return new PCData();
    }
}
