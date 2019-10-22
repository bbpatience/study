package com.walle.ratelimit;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessCtrl {

    /**
     * access limit per second.
     * @return
     */
    int limit() default 5;
}
