package synctrl;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: bbpatience
 * @date: 2018/12/4
 * @description: Deadlock , interrupt thread.
 **/
public class IntLock implements Runnable {

    public int lock;
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();

    public IntLock(final int i) {
        lock = i;
    }

    @Override
    public void run() {
        try {
            if (this.lock == 1) {
                lock1.lockInterruptibly();

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {}
                lock2.lockInterruptibly();
                System.out.println("normal11 quit");
            } else {
                lock2.lockInterruptibly();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {}
                lock1.lockInterruptibly();
                System.out.println("normal22 quit");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getName() + " quit");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        IntLock lock1 = new IntLock(1);
        IntLock lock2 = new IntLock(2);

        Thread t1 = new Thread(lock1);
        Thread t2 = new Thread(lock2);

        t1.start(); t2.start();
//        t1.join(); t2.join();

        Thread.sleep(10000);
        t2.interrupt();
    }
}
