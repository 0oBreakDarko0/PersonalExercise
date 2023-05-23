package org.myself.study.pattern.proxy;

public class Client {
    public static void main(String[] args) {
        MyInterface myInterface = new MyInterfaceImpl();
        MyInterface myProxy = new MyProxy(myInterface);

        myProxy.proxy();
    }
}
