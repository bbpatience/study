package com.walle.concurrent.synctrl;

/**
 * @author: bbpatience
 * @date: 2020/3/20
 * @description: VolatileVisibilityTest
 **/
public class VolatileVisibilityTest {

    private static volatile boolean initFlag = false;
//    private static boolean initFlag = false;
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            System.out.println("waiting data");
            while (!initFlag) {

            }
            System.out.println("=================== success");
        }).start();

        Thread.sleep(2000);

        new Thread(VolatileVisibilityTest::prepareData).start();
    }

    public static void prepareData() {
        System.out.println("preparing data...");
        initFlag = true;
        System.out.println("preparing data end...");
    }
}
