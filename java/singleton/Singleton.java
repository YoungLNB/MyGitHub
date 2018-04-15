package com.vince.singleton;
/**
 * 单例模式的优化：
 * 1、使用同步保证线程安全，防止多线程访问的不安全性
 * 2、使用volatile修饰符，保证变量的一致性
 * 3、防止反射调用私有构造
 * 4、让单例类可以被序列化
 */

import java.io.Serializable;

public class Singleton implements Serializable {
    private volatile static Singleton singleton = null;

    public Singleton() {
        if(singleton!=null){
            throw new RuntimeException("此类为单例模式，已经被实例化了");
        }
    }
    public static Singleton getInstance(){
        if(singleton==null){
            synchronized (Singleton.class){
                if(singleton==null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
