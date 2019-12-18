package com.xy.vedio;

/**
 * Created by asus on 2019/8/7.
 */
public class jvmLoaderDemo {
    public static void main(String[] args) {
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));
    }
}
