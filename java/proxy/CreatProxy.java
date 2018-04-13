package com.vince.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CreatProxy implements InvocationHandler {
    private Object target;
    //创建代理对象的方法
    public Object Create(Object target) {
        this.target = target;
        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
        return proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("寻找客户所需要的东西");
        System.out.println("跟客户确认物品");
        method.invoke(target,args);
        System.out.println("完成本次海淘");
        return null;
    }
}
