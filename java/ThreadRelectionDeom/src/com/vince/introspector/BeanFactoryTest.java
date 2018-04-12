package com.vince.introspector;


import org.junit.Test;

/**
 * 通过内省的API来装配一个Bean对象，Bean对象的值是通过配置文件来获取的，目的是为了提高代码的维护性
 */
public class BeanFactoryTest {
    @Test
    public void test(){
        Config getbean = (Config) BeanFactory.getbean("bean.name");
        System.out.println(getbean);
    }
}
