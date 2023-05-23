package org.myself.study.pattern.proxy.dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyJdkProxy implements InvocationHandler {

    private MyInterface source;

    public MyJdkProxy(MyInterface source) {
        this.source = source;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是JDK动态代理：增强开始");
        Object invoke = method.invoke(source, args);
        System.out.println("我是JDK动态代理：增强结束");
        return invoke;
    }
}
