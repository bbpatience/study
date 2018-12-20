package com.walle.concurrent.future.jdkfuture;

import java.util.concurrent.Callable;

/**
 * @author: bbpatience
 * @date: 2018/12/20
 * @description: RealData
 **/
public class RealData implements Callable<String> {

    private String para;

    public RealData(String para) {
        this.para = para;
    }

    @Override
    public String call() throws Exception {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(para);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
