package com.walle.concurrent.future.selfcreate;

/**
 * @author: bbpatience
 * @date: 2018/12/12
 * @description: Client
 **/
public class Client {
    public Data request(final String queryStr) {
        final FutureData future = new FutureData();

        new Thread() {
            public void run() {
                RealData realData = new RealData(queryStr);
                future.setRealData(realData);
            }
        }.start();
        return future;
    }

    public static void main(String[] args) throws InterruptedException {
        Client client = new Client();
        Data data = client.request("name");
        System.out.println("request done");

        Thread.sleep(2000);
        System.out.println("data:" + data.getResult());
    }
}
