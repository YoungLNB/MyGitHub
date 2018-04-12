package com.vince.Bean;


import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class BeanTest {
    @Test
    public void test(){
        String name = "小白";
        int age = 1;
        Dog dog = new Dog();
        try {
            BeanUtils.setProperty(dog,"name",name);
            BeanUtils.setProperty(dog,"age",age);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(dog.toString());

    }
}
