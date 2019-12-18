package com.xy.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {

    public void newInstanceCacheThreadPool(){
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    }

}
