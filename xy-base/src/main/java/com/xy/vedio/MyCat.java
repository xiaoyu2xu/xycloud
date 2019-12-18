package com.xy.vedio;

/**
 * Created by asus on 2019/8/7.
 */
public class MyCat {

    public MyCat() {
        System.out.println("MyCat is loaded by: " + this.getClass().getClassLoader());
    }
}
