package com.desigin_pattern.Memento_pattern;

/**
 * 备忘录
 */
public class Memento {
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Memento(int state) {
        this.state = state;
        System.out.println("备忘录当前保存状态："+state);
    }
}
