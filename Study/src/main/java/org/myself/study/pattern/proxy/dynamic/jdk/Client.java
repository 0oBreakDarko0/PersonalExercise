package org.myself.study.pattern.proxy.dynamic.jdk;

import java.lang.reflect.Proxy;

/**
 * @author mapengfei
 */
public class Client {
    public static void main(String[] args) {
        MyInterfaceImpl myInterface = new MyInterfaceImpl();
        MyInterface proxy = (MyInterface) Proxy.newProxyInstance(myInterface.getClass().getClassLoader(), myInterface.getClass().getInterfaces(), new MyJdkProxy(myInterface));
        proxy.proxy();
    }
}
