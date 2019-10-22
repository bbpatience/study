package com.walle.ratelimit;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: bbpatience
 * @date: 2019/10/21
 * @description: AccessLimit
 **/
public class AccessLimit {

    public static ConcurrentHashMap<Long, RateLimiter> limitMap = new ConcurrentHashMap<>();

    public static boolean tryAcquire(Long appId, Class<?> clazz) {
        RateLimiter rateLimiter = limitMap.get(appId);
        if (rateLimiter == null) {
            if (clazz.isAnnotationPresent(AccessCtrl.class)) {
                AccessCtrl access = clazz.getAnnotation(AccessCtrl.class);
                rateLimiter = RateLimiter.create(access.limit());
            } else {
                rateLimiter = RateLimiter.create(5);
            }
            limitMap.putIfAbsent(appId, rateLimiter);
        }
        return rateLimiter.tryAcquire();
    }
}
