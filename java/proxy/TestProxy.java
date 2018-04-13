package com.vince.proxy;

import org.junit.Test;

public class TestProxy {
    @Test
    public void Test(){
        Subject person = new Person();
        //创建代理对象的对象
        CreatProxy creatProxy = new CreatProxy();
        Subject proxy = (Subject) creatProxy.Create(person);
        proxy.shopping();
    }
}
