package com.vince.aop;

import org.junit.Test;

import java.io.InputStream;

public class AOPTest {
    @Test
    public void Test(){
        //1.读取配置文件
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/vince/aop/bean.properties");
        //2.创建bean的工厂对象
        BeanFactory beanFactory = new BeanFactory(resourceAsStream);
        //3.获取代理对象
        ProxyFactory bean = (ProxyFactory) beanFactory.getBean("bean");
        IManger proxy = (IManger) bean.getProxy();
        proxy.add("我喜欢小白");
    }
}
