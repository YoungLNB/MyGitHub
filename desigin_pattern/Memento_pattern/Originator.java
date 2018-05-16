package com.desigin_pattern.Memento_pattern;

/**
 * 发起者
 * 1.负责创造备忘录,记录对象内部状态
 * 2.使用备忘录恢复状态
 */
public class Originator {
    private int state = 0;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        System.out.println("发起人更改状态："+state);
    }
    //创建备忘录对象
    public Memento CreateMenmentoObject(){
        return new Memento(state);
    }
    //恢复备忘录状态
    public void restoreMemento(Memento memento){
        this.state = memento.getState();
        System.out.println("恢复备忘录状态："+state);
    }
}
