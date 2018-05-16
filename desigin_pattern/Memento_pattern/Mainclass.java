package com.desigin_pattern.Memento_pattern;

/**
 * "白箱"备忘录
 */
public class Mainclass {
    public static void main(String[] args) {
        int state = 3;
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();
        //设置发起者的初始状态
        originator.setState(state);
        caretaker.saveMemento(originator.CreateMenmentoObject());
        //对发起者重新更改状态
        originator.setState(5);
        originator.restoreMemento(caretaker.retrieveMemento());
    }
}
