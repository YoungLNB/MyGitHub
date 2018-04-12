package com.vince.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadDeom5 {
    public static void main(String[] args) {
        //创建线程池（4种方法）
        //1.创建一个单线程的线程池
//        ExecutorService es = Executors.newSingleThreadExecutor();
        //2.创建一个固定大小的线程池
//        ExecutorService es = Executors.newFixedThreadPool(1);
        //3.创建一个带缓冲的线程池
//        ExecutorService es = Executors.newScheduledThreadPool(3);
//        es.execute(new MyRunnable6());
//        es.execute(new MyRunnable6());
        //4.创建一个无限大的线程池
        ScheduledExecutorService es = Executors.newScheduledThreadPool(3);
        es.schedule(new MyRunnable6(), 3000, TimeUnit.MILLISECONDS);
        //停止线程池
        es.shutdown();
    }
}

class MyRunnable6 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "-" + i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
