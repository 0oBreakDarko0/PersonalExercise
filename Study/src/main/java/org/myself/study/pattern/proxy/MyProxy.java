package org.myself.study.pattern.proxy;

public class MyProxy implements MyInterface{
    private MyInterface myInterface;

    public MyProxy(MyInterface myInterface) {
        this.myInterface = myInterface;
    }

    @Override
    public void proxy() {
        System.out.println("我是代理类：增强开始");
        myInterface.proxy();
        System.out.println("我是代理类：增强结束");
    }
}
