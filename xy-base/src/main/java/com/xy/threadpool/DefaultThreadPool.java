package com.xy.threadpool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    /**
     * 线程池最大限制数
     */
    private static final int MAX_WORKER_NUMBERS = 10;

    /**
     * 线程池默认的数量
     */
    private static final int DEFAULT_WORKER_NUMBERS = 5;

    /**
     * 线程池最小的数量
     */
    private static final int MIN_WORKER_NUMBERS = 1;

    /**
     * 工作列表，往里面出入工作
     */
    private final LinkedList<Job> jobs = new LinkedList<>();

    /**
     * 工作者列表
     */
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<>());

    /**
     * 工作者线程数量
     */
    private int workerNum = DEFAULT_WORKER_NUMBERS;

    /**
     * 线程编号生成
     */
    private AtomicLong threadNum = new AtomicLong();

    public DefaultThreadPool() {
        initializeWorkers(DEFAULT_WORKER_NUMBERS);
    }

    private void initializeWorkers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "ThreadPool-Worker-" + threadNum.incrementAndGet());
            thread.start();
        }
    }

    public DefaultThreadPool(int workerNum) {
        this.workerNum = workerNum > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : workerNum < MIN_WORKER_NUMBERS ? MIN_WORKER_NUMBERS : workerNum;
        initializeWorkers(this.workerNum);
    }

    @Override
    public void execute(Job job) {
        if(job != null){
            synchronized (jobs){
                jobs.addLast(job);
                jobs.notifyAll();
            }
        }
    }

    @Override
    public void shutdown() {
        for (Worker worker: workers) {
            worker.shutdown();
        }
    }

    @Override
    public void addWorkers(int num) {
        synchronized (jobs){
            //限制新增的Worker数量不能超过最大值
            if(num + this.workerNum > MAX_WORKER_NUMBERS){
                num = MAX_WORKER_NUMBERS - this.workerNum;
            }
            initializeWorkers(num);
            this.workerNum += num;
        }
    }

    @Override
    public void removeWorker(int num) {
        synchronized (jobs){
            if(num >= this.workerNum){
                throw new IllegalArgumentException("beyond workNum");
            }
            int count = 0;
            while (count < num){
                Worker worker = workers.get(count);
                if(workers.remove(worker)){
                    worker.shutdown();
                    count ++;
                }
            }
            this.workerNum -= count;
        }
    }

    @Override
    public int getJobSize() {
        return jobs.size();
    }

    class Worker implements Runnable {
        /**
         * 是否工作
         */
        private volatile boolean running = false;
        @Override
        public void run() {
            while (running){
                Job job = null;
                synchronized (jobs){
                    while (jobs.isEmpty()){
                        try {
                            job.wait();
                        } catch (InterruptedException ex){
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    job = jobs.removeFirst();
                }
                if (job != null){
                    try {
                        job.run();
                    } catch (Exception ex){
                        //忽略job执行中的Exception
                    }
                }
            }
        }

        public void shutdown(){
            running = false;
        }
    }
}
