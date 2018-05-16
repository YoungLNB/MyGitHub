package com.desigin_pattern.visitor;

public interface Visitor {
    public void visit( ConcreteElementA elementA );
    public void visit( ConcreteElementB elementB );
}
