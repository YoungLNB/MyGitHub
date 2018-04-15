package com.vince.mina;

import java.io.Serializable;
//实现对象的传输需要实现 Serializable接口，该接口是一个标记接口，无任何定义，只是为了告诉JVM该类可以被序列化
public class Message implements Serializable {
    private String from;
    private String to;
    private String info;
    private String type;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Message{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", info='" + info + '\'' +
                ", type='" + type + '\'' +
                '}';
    }


}
