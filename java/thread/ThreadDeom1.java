package com.vince.thread;

public class ThreadDeom1 {
    public static void main(String[] args){
        MyThread mt = new MyThread();
        mt.start();

        MyRunnable1 mr = new MyRunnable1();
        Thread t = new Thread(mr);
        t.start();
    }
}
//线程实现的一种方式：继承Thread类
class MyThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i <1000 ; i++) {
            System.out.println(Thread.currentThread().getName()+"-"+i);
            try {
                Thread.sleep(500);//线程的休眠，使当前的线程以指定的毫秒暂停，释放CPU时间片，对象锁不释放
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
//线程实现的另一种方式：实现Runnable接口
class MyRunnable1 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i <1000 ; i++) {
            System.out.println(Thread.currentThread().getName()+"-"+i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
