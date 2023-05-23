package org.myself.study.pattern.proxy.dynamic.jdk;

/**
 * @author mapengfei
 */
public class MyInterfaceImpl implements MyInterface{
    @Override
    public void proxy() {
        System.out.println("我是JDK动态代理被代理类");
    }
}
