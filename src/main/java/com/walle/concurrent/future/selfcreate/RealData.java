package com.walle.concurrent.future.selfcreate;

/**
 * @author: bbpatience
 * @date: 2018/12/12
 * @description: RealData
 **/
public class RealData implements Data {

    protected final String result;

    public RealData(String param) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.result = sb.toString();
    }

    @Override
    public String getResult() {
        return this.result;
    }
}
