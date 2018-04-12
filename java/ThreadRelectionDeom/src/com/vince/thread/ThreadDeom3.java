package com.vince.thread;

public class ThreadDeom3 {
    public static void main(String[] args){
        MyRunnable4 mr4 = new MyRunnable4();
        Thread t = new Thread(mr4);
        t.setDaemon(true);  //将此线程标记为守护线程，当运行的唯一线程为守护线程时，java虚拟机将退出
        t.setPriority(Thread.MAX_PRIORITY);//优先级高可以提高线程抢占CPU时间片的概率
        System.out.println(t.isAlive());
        t.start();
        System.out.println(t.isAlive());
        for (int i = 0; i < 50; i++) {
            System.out.println(Thread.currentThread().getName()+"-"+i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
class MyRunnable4 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println(Thread.currentThread().getName()+"-"+i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
