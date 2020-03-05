package com.xy.jvm;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxyDemo implements InvocationHandler {

    private Object sub;

    public DynamicProxyDemo(Object sub) {
        this.sub = sub;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("before calling " + method);

        method.invoke(this.sub, objects);

        System.out.println("after calling " + method);
        return null;
    }
}
