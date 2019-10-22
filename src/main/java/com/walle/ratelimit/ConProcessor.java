package com.walle.ratelimit;


/**
 * @author: bbpatience
 * @date: 2019/1/4
 * @description: Apple
 **/
@AccessCtrl(limit = 1)
public class ConProcessor {

    public static void main(String[] args) {
//        AnnotationUtils.getAnnotationInfo(Apple.class);
        ConProcessor processor = new ConProcessor();
        int i = 0;
        while (i < 20) {
            if (AccessLimit.tryAcquire(1L, processor.getClass())) {
                System.out.println("get Access");
            } else {
                System.out.println("Not get Access");
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }
}
