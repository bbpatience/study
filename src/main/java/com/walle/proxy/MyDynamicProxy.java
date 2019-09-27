package com.walle.proxy;

import java.lang.reflect.Proxy;

/**
 * @author: bbpatience
 * @date: 2019/9/27
 * @description: MyDynamicProxy
 **/
public class MyDynamicProxy {
    public static void main (String[] args) {
        HelloImpl hello = new HelloImpl();
        MyInvocationHandler handler = new MyInvocationHandler(hello);
        Hello proxyHello = (Hello) Proxy.newProxyInstance(HelloImpl.class.getClassLoader(), HelloImpl.class.getInterfaces(), handler);
        proxyHello.sayHello();
    }
}
