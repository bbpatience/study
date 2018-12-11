package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: bbpatience
 * @date: 2018/12/11
 * @description: ExtThreadPool
 **/
public class ExtThreadPool {
    public static class MyTask implements Runnable {
        public String taskName;

        public MyTask(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public void run() {
            System.out.println("Executing now " + " Thread ID:" + Thread.currentThread().getId());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>()) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("before executing ." + t.getName());
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("after executing .");
            }

            @Override
            protected void terminated() {
                System.out.println("Thread pool exits.");
            }
        };

        for (int i = 0; i < 5 ; i++) {
            MyTask myTask = new MyTask("TASK-" + i);
            es.submit(myTask);
            Thread.sleep(10);
        }
        es.shutdown();
    }
}
