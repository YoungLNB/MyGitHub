package com.desigin_pattern.visitor;

public interface Element {
    public void accept(Visitor visitor);
}
