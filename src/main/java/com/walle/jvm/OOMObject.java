package com.walle.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: bbpatience
 * @date: 2019/8/2
 * @description: OOMObject
 **/
public class OOMObject {

    public byte[] placeholder = new byte[64 * 1024];

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num ; i++) {
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws Exception {
        fillHeap(1000);
//        System.gc();
    }
}
