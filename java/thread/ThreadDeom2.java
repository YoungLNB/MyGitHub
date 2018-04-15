package com.vince.thread;

/**
 * join和中断线程
 * 中断线程：
 *      （1）使用interrupt方法来中断线程，设置一个中断状态
 *      （2）使用自定义标记的方式（推荐使用）
 */

public class ThreadDeom2 {
    public static void main(String[] args)  {
//        MyRunnable2 mr2 = new MyRunnable2();
//        Thread t = new Thread(mr2);
//        t.start();
        MyRunnable3 mr3 = new MyRunnable3();
        Thread t3 = new Thread(mr3);
        t3.start();
        for (int i = 0; i < 50; i++) {
            System.out.println(Thread.currentThread().getName()+"-"+i);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(i==20){
                //加入线程，让调用的线程限制性指定时间或者执行完毕
//                try {
//                    t.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                t.interrupt();
                mr3.flag = false;
            }
        }


    }
}
class MyRunnable2 implements Runnable{
    @Override
    public void run() {

        for (int i = 0; i < 50; i++) {
            if(Thread.interrupted())//此方法会把中断状态清除
                break;
            System.out.println(Thread.currentThread().getName()+"-"+i);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
class MyRunnable3 implements Runnable{
    public boolean flag = true;
    public int i = 0;
    public MyRunnable3(){
        flag = true;
    }
    @Override
    public void run() {
        while(flag){
            System.out.println(Thread.currentThread().getName()+"--"+i++);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
