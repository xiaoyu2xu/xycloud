package com.xy.vedio;

/**
 * Created by asus on 2019/8/7.
 */
public class MySample {

    public MySample() {
        System.out.println("MySample is loaded by: " + this.getClass().getClassLoader());
        new MyCat();
    }
}
