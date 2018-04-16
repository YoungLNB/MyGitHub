package com.vince.aop;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class BeanFactory {
    Properties prop = new Properties();
    public BeanFactory(InputStream in){
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //获取一个bean实例
    public Object getBean(String name){
        String classname = prop.getProperty(name);
        Object bean = null;
        try {
            //获取ProxyFactoryBean的class对象
            Class<?> aClass = Class.forName(classname);
            bean = aClass.newInstance();
            //根据配置文件实例化target和advice
            Object target = Class.forName(prop.getProperty(name + ".target")).newInstance();
            Object advice = Class.forName(prop.getProperty(name + ".advice")).newInstance();
            //通过内省实现对ProxyFactory对象属性的赋值
            BeanInfo beanInfo = Introspector.getBeanInfo(aClass);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                String propertyname = propertyDescriptors[i].getName();
                Method writeMethod = propertyDescriptors[i].getWriteMethod();
                if("target".equals(propertyname)){
                    writeMethod.invoke(bean,target);
                }else if("advice".equals(propertyname)){
                    writeMethod.invoke(bean,advice);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }
}
