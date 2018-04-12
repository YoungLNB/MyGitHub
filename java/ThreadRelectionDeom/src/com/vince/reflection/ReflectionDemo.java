package com.vince.reflection;

import org.junit.Test;

import java.lang.reflect.*;

public class ReflectionDemo {
    @Test
    //获取所有构造方法
    public void Test1(){
        Class c = Dog.class;
        Constructor[] constructors = c.getConstructors();
        for (int i = 0; i < constructors.length; i++) {
            System.out.println(constructors[i].getName());
            //获取构造方法中参数的个数
            System.out.println(constructors[i].getParameterCount());
        }
        try {
            //调用带参数的构造器
            Constructor constructor = c.getConstructor(String.class,int.class,String.class);
            Dog dog = (Dog) constructor.newInstance("小白",1,"白色");
            System.out.println(dog.toString());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
    //获取对象的非私有属性
    @Test
    public void Test2(){
        Class<Dog> dogClass = Dog.class;
        Field[] fields = dogClass.getFields();
        System.out.println(fields.length);
        Field[] declaredFields = dogClass.getDeclaredFields();
        System.out.println(declaredFields.length);
        for (int i = 0; i < declaredFields.length; i++) {
            int modifiers = declaredFields[i].getModifiers();
            System.out.println(Modifier.toString(modifiers)+" "+declaredFields[i].getType()+" "+declaredFields[i].getName());
        }

    }
    //访问私有方法
    @Test
    public  void Test3(){
        Class<Dog> dogClass = Dog.class;
        Package aPackage = dogClass.getPackage();
        System.out.println(aPackage.getName());
        Method[] methods = dogClass.getMethods();
        Method[] declaredMethods = dogClass.getDeclaredMethods();
        for (int i = 0; i < declaredMethods.length; i++) {
            System.out.println(declaredMethods[i]);
        }
//        for (int i = 0; i < methods.length; i++) {
//            System.out.println(methods[i]);
//        }

    }
    @Test
    public void test4(){
        Dog dog = new Dog("小白",1,"白色");
        Class<Dog> dogClass = Dog.class;
        Method[] methods = dogClass.getMethods();
        for (int i = 0; i < methods.length; i++) {
            if(methods[i].getName().equals("toString")){
                try {
                    System.out.println(methods[i].invoke(dog));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        //得到Class类的对象的三种方式
        Dog dog = new Dog("小白",1,"白色");
        Class c1 = dog.getClass();
        Class c2 = Dog.class;
        try {
            Class c3 = Class.forName("com.vince.reflection.Dog");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
