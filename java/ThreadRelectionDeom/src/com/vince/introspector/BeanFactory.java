package com.vince.introspector;

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
    private static Properties pro = new Properties();
    //使用静态代码块读取配置文件
    static{
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/vince/introspector/config.properties");
        try {
            pro.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Object getbean(String name){
        Object bean = null;
        String property = pro.getProperty(name);
        try {
            Class<?> aClass = Class.forName(property);
            bean = aClass.newInstance();
            //通过类信息获取javabean的描述信息
            BeanInfo beanInfo = Introspector.getBeanInfo(aClass);
            //通过javabean的描述信息，获取该类的所有属性信息
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                String propertyname = propertyDescriptors[i].getName();
                Method writeMethod = propertyDescriptors[i].getWriteMethod();
                if("username".equals(propertyname)){
                    //调用属性的set方法
                    writeMethod.invoke(bean,pro.getProperty("bean.username"));
                }else if("password".equals(propertyname)){
                    writeMethod.invoke(bean,pro.getProperty("bean.password"));
                }else if("url".equals(propertyname)){
                    writeMethod.invoke(bean,pro.getProperty("bean.url"));
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }

}
