package org.myself.study.pattern.proxy.dynamic.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author mapengfei
 */
public class MyInterceptor implements MethodInterceptor {

    private MyInterface source;

    public MyInterceptor(MyInterface source) {
        this.source = source;
    }

    public Object getProxyInstance() {
        Enhancer enhancer = new Enhancer();
        //设置父类
        enhancer.setSuperclass(source.getClass());
        //设置回调函数
        enhancer.setCallback(this);

        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("CGLIB动态代理：增强开始");
        Object invoke = method.invoke(source, objects);
        System.out.println("CGLIB动态代理：增强结束");
        return invoke;
    }
}
