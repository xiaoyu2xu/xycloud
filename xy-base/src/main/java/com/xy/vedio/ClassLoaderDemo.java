package com.xy.vedio;

import java.io.*;

/**
 * Created by asus on 2019/8/2.
 */
public class ClassLoaderDemo extends ClassLoader {

    private String classLoaderName;

    private final String fileExtension = ".class";

    private String path;

    public void setPath(String path) {
        this.path = path;
    }

    public ClassLoaderDemo() {
        super();
    }

    public ClassLoaderDemo(String classLoaderName) {
        super();
        this.classLoaderName = classLoaderName;
    }

    public ClassLoaderDemo(ClassLoader parent, String classLoaderName) {
        super(parent);
        this.classLoaderName = classLoaderName;
    }



    @Override
    public String toString() {
        return "{" +
                 this.classLoaderName
                + '}';
    }

    private byte[] loadClassData(String name){
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;

        try {
            this.classLoaderName = this.classLoaderName.replace(".","/");
            is = new FileInputStream(new File(name + this.fileExtension));
            baos = new ByteArrayOutputStream();

            int ch = 0;

            while ( -1 != (ch = is.read())){
                baos.write(ch);
            }
            data = baos.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                is.close();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("findClass invoked " + name);
        System.out.println("class Loader name " + this.classLoaderName);

        byte[] data = this.loadClassData(name);
        return this.defineClass(name,data,0,data.length);
    }

    public static void test(ClassLoader classLoader) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clazz = classLoader.loadClass("com.zxy.study.jvm.vedio.Test1");
        Object object = clazz.newInstance();

        System.out.println(object);
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        ClassLoaderDemo loader1 = new ClassLoaderDemo();
        //test(loader1);
    }
}
