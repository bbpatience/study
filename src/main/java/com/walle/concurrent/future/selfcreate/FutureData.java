package com.walle.concurrent.future.selfcreate;

/**
 * @author: bbpatience
 * @date: 2018/12/12
 * @description: FutureData
 **/
public class FutureData implements Data {

    protected RealData realData = null;
    protected boolean isReady = false;

    public synchronized void setRealData(RealData data) {
        if (isReady) {
            return;
        }
        this.realData = data;
        isReady = true;
        notifyAll();
    }

    public synchronized String getResult() {
        while (!isReady) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this.realData.result;
    }
}
