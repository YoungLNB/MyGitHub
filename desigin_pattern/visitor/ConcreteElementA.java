package com.desigin_pattern.visitor;

public class ConcreteElementA implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void operationA(){
        System.out.println("ConcreteElementA");
    }
}
