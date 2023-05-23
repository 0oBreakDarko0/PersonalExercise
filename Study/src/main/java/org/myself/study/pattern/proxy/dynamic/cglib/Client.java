package org.myself.study.pattern.proxy.dynamic.cglib;

public class Client {

    public static void main(String[] args) {
        MyInterface myInterface = new MyInterfaceImpl();
        MyInterface proxy = (MyInterface) new MyInterceptor(myInterface).getProxyInstance();
        proxy.proxy();
    }
}
