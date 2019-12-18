package com.xy.vedio;

/**
 * Created by asus on 2019/8/9.
 */
public class ContextLoaderDemo1 implements Runnable {
    private Thread thread;

    public ContextLoaderDemo1() {
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        ClassLoader classLoader = this.thread.getContextClassLoader();

        this.thread.setContextClassLoader(classLoader);

        System.out.println("Class: " + classLoader.getClass());
        System.out.println("Parent: " + classLoader.getParent().getClass());

    }

    public static void main(String[] args) {
        new ContextLoaderDemo1();
    }
}
