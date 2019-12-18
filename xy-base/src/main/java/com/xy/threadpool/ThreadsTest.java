package com.xy.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadsTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future> list = new ArrayList<Future>();
        Callable c = new Callable(){

            public Object call() throws Exception {
                return "调用call方法";
            }
        };

        Future f = pool.submit(c);

        pool.shutdown();

        System.out.println(f.get());
    }
}
