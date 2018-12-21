package com.walle.io.nio;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

/**
 * @author: bbpatience
 * @date: 2018/12/21
 * @description: HandleMsg
 **/
public class HandleMsg implements Runnable {
    SelectionKey key;
    ByteBuffer bb;
    Selector selector;

    public HandleMsg(SelectionKey key, ByteBuffer bb, Selector selector) {
        this.key = key;
        this.bb = bb;
        this.selector = selector;
    }

    @Override
    public void run() {
        EchoClient echoClient = (EchoClient) key.attachment();
        echoClient.enqueue(bb);
        key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        selector.wakeup();
    }
}
