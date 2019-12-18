package com.xy.vedio;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by asus on 2019/8/7.
 */
public class MyTest20 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        ClassLoaderDemo loader1 = new ClassLoaderDemo("loader1");
        ClassLoaderDemo loader2 = new ClassLoaderDemo("loader2");

        Class<?> clazz1 = loader1.loadClass("com.zxy.study.jvm.vedio.MyPerson");
        Class<?> clazz2 = loader2.loadClass("com.zxy.study.jvm.vedio.MyPerson");

        System.out.println(clazz1 == clazz2);

        Object object1 = clazz1.newInstance();
        Object object2 = clazz2.newInstance();

        Method method = clazz1.getMethod("setMyPerson",Object.class);
        method.invoke(object1,object2);
    }

}
