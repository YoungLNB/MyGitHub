package com.vince.communication;

import java.io.Serializable;

/**
 * 消息类
 */
public class Message implements Serializable {
    private String fromname;  //发送者
    private String toname;    //接受者
    private String info;      //发送的消息
    private int type;         //消息类型

    @Override
    public String toString() {
        return "Message{" +
                "fromname='" + fromname + '\'' +
                ", toname='" + toname + '\'' +
                ", info='" + info + '\'' +
                ", type=" + type +
                '}';
    }

    public Message() {
    }

    public Message(String fromname, String toname, String info, int type) {
        this.fromname = fromname;
        this.toname = toname;
        this.info = info;
        this.type = type;
    }

    public String getFromname() {
        return fromname;
    }

    public void setFromname(String fromname) {
        this.fromname = fromname;
    }

    public String getToname() {
        return toname;
    }

    public void setToname(String toname) {
        this.toname = toname;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}