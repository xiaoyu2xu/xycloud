package com.xy.vedio;

/**
 * Created by asus on 2019/8/7.
 */
public class MyTest1 {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoaderDemo classLoaderDemo = new ClassLoaderDemo("loader1");

        Class<?> clazz = classLoaderDemo.loadClass("com.zxy.study.jvm.vedio.MySample");
        System.out.println("class:" + clazz.hashCode());
        //如果注释掉该行，则不会初始化MySample对象，构造方法不会被调用
        //因此不会实例化MyCat对象，即没有对MyCat进行主动使用，这里就不会加载MyCat Class
        Object object = clazz.newInstance();
    }
}
