package com.desigin_pattern.visitor;

public class ConcreteVisitorA implements Visitor{

    @Override
    public void visit(ConcreteElementA elementA) {
        elementA.operationA();
    }

    @Override
    public void visit(ConcreteElementB elementB) {
        elementB.operationB();
    }
}
