package com.desigin_pattern.visitor;

public class MainClass {
    public static void main(String[] args) {
        ObjectStructure objectStructure = new ObjectStructure();
//        objectStructure.add(new ConcreteElementA());
        objectStructure.add(new ConcreteElementB());
        Visitor visitor = new ConcreteVisitorA();
        objectStructure.action(visitor);
    }
}
