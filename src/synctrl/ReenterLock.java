package synctrl;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: bbpatience
 * @date: 2018/12/4
 * @description: main.java.ReenterLock
 **/
public class ReenterLock implements Runnable {

    private static ReentrantLock lock = new ReentrantLock();

    private static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            lock.lock();
            lock.lock();
            try {
                i++;
            } finally {
                lock.unlock();
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLock tl = new ReenterLock();

        Thread t1 = new Thread(tl);
        Thread t2 = new Thread(tl);
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(i);
    }
}
