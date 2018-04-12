package com.vince.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程同步
 * 线程共享数据会导致线程不安全，所以必须要使用同步
 * 同步是指多个线程在同一时间内只能有一个线程执行指定代码，其他线程要等待此线程结束之后才可以继续执行
 * 线程实现同步的三个方法：
 * （1）同步代码块 Synchronized
 * （2）同步方法
 * （3）Lock(更灵活的代码控制)
 */
public class ThreadDeom4 {
    public static void main(String[] args){
        MyRunnable5 mr5 = new MyRunnable5();
        Thread t1 = new Thread(mr5);
        Thread t2 = new Thread(mr5);
        t1.start();
        t2.start();
    }
}

class MyRunnable5 implements Runnable{
    private int ticket = 10;   //剩余票数
    ReentrantLock lock = new ReentrantLock();  //互斥锁

    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            //同步代码块
//            synchronized (this){
//                if(ticket>0){
//                    ticket--;
//                    System.out.println("您购买的车票剩余"+ticket+"张...");
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//          Method();
            lock.lock();//锁
            if (ticket > 0) {
                    ticket--;
                    System.out.println("您购买的车票剩余"+ticket+"张...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            lock.unlock();//解锁

            }
        }
    }
//        //同步方法
//        public synchronized void Method(){
//        if(ticket>0){
//            ticket--;
//            System.out.println("您购买的车票剩余"+ticket+"张...");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }