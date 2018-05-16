package com.desigin_pattern.Memento_pattern;

/**
 * 备忘录管理
 */
public class Caretaker {
    private Memento memento;
    //恢复当前状态
    public Memento retrieveMemento(){
        return this.memento;
    }
    //存储备忘录
    public void saveMemento( Memento memento){
        this.memento = memento;
    }
}
