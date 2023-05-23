package org.myself.study.pattern.proxy.dynamic.cglib;

/**
 * @author mapengfei
 */
public class MyInterfaceImpl extends MyInterface{

    @Override
    public void proxy() {
        System.out.println("我是cglib动态代理被代理类");
    }
}
