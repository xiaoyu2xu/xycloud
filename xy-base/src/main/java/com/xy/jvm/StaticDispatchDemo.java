package com.xy.jvm;

/**
 * 方法的静态分派
 * Grandpa g1 = new Father();
 * 以上代码，g1的静态类型是Grandpa，而g1的实际类型（真正指向的类型）是Father。
 * 我们可以得出这样一个结论：变量的静态类型是不会发生变化的，而变量的实际类型则是可以发生变化的（多态的一种体现），
 * 实际类型是在运行期方可确定
 */
public class StaticDispatchDemo {

    /**
     * 方法重载，是一种静态的行为，编译器完全可以确定
     * @param grandpa
     */
    public void test(Grandpa grandpa){
        System.out.println("grandpa");
    }

    public void test(Father father){
        System.out.println("father");
    }

    public void test(Son son){
        System.out.println("son");
    }

    public static void main(String[] args) {
        Grandpa g1 = new Father();
        Grandpa g2 = new Son();

        StaticDispatchDemo staticDispatchDemo = new StaticDispatchDemo();
        staticDispatchDemo.test(g1);
        staticDispatchDemo.test(g2);

        System.out.println("---------分割线---------");
        staticDispatchDemo.test((Father)g1);
        staticDispatchDemo.test((Son)g2);
    }
}

class Grandpa{

}

class Father extends Grandpa{

}

class Son extends Father{

}