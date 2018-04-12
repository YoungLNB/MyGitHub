package com.vince.thread;

/**
 * 生产者与消费者模式
 */
public class ProductAndCustomerDeom {
    public static void main(String[] args) {
        Food food = new Food();
        Product p = new Product(food);
        Customer c = new Customer(food);
        Thread t1 = new Thread(p);
        Thread t2 = new Thread(c);

        t1.start();
        t2.start();
    }
}
//生产者
class Product implements Runnable{
    private Food food;
    public Product(Food food){
        this.food = food;
    }
    @Override
    public  void run() {
        for (int i = 0; i < 20; i++) {
            if(i%2==0){
                food.set("纸包鱼","好吃");
            }else{
                food.set("花田煮","最爱");
            }

        }
    }
}
//消费者
class Customer implements Runnable{
    private Food food;
    public Customer(Food food){
        this.food = food;
    }
    @Override
    public  void run() {
        for (int i = 0; i < 20; i++) {
            food.get();
        }

    }
}
class Food{
    private String name;
    private String describe;
    private boolean flag = true;   //假定true生产，flase消费

    public synchronized void set(String name, String describe) {
        //不能生产
        if (!flag) {
            try {
                this.wait();//线程进入等待状态，释放监视器的所有权
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.setName(name);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.setDescribe(describe);
        flag = false;
        this.notify();
    }
    public synchronized void get(){
        //不能消费
        if(flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getName()+"->"+this.getDescribe());
        flag = true;
        this.notify();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Food(String name, String describe) {
        this.name = name;
        this.describe = describe;
    }

    public Food() {
    }
}
