package org.myself.study.pattern.proxy;

/**
 * @author mapengfei
 */
public class MyInterfaceImpl implements MyInterface{
    @Override
    public void proxy() {
        System.out.println("我是静态被代理类");
    }
}
