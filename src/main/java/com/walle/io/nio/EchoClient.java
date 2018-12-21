package com.walle.io.nio;

import java.nio.ByteBuffer;
import java.util.LinkedList;

/**
 * @author: bbpatience
 * @date: 2018/12/21
 * @description: EchoClient
 **/
public class EchoClient {
    private LinkedList<ByteBuffer> outq;

    public EchoClient() {
        this.outq = new LinkedList<>();
    }

    public LinkedList<ByteBuffer> getOutq() {
        return outq;
    }

    public void enqueue(ByteBuffer bf) {
        outq.addFirst(bf);
    }
}
