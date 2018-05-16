package com.desigin_pattern.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 对象结构
 */
public class ObjectStructure {
    private List<Element> list = new ArrayList<>();
    public void action(Visitor visitor){
        for (Element e :
                list) {
            e.accept(visitor);
        }
    }
    public void add(Element element){
        list.add(element);
    }
}
