package com.xy.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DemoForCas {
    private int i = 0;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        final DemoForCas cas = new DemoForCas();
        List<Thread> ts = new ArrayList<Thread>(600);
        long start = System.currentTimeMillis();

        for (int j = 0; j < 100; j++) {
            Thread t = new Thread(() -> {
                for (int i = 0; i < 10000; i++) {
                    cas.count();
                    cas.safeCount();
                }
            });
            ts.add(t);
        }
        for (Thread t: ts) {
            t.start();
        }
        /**
         * 等待所有线程执行完成
         */
        for (Thread t : ts){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Cas's i is " + cas.i);
        System.out.println("Cas's atomicI is " + cas.atomicInteger.get());
        System.out.println(System.currentTimeMillis() - start);
    }

    /**
     * 使用CAS实现线程安全计数器
     */
    private void safeCount() {
        for (;;) {
            int i = atomicInteger.get();
            boolean suc = atomicInteger.compareAndSet(i, ++i);
            if(suc){
                break;
            }
        }
    }

    /**
     * 非线程安全计数器
     */
    private void count() {
        i++;
    }
}
