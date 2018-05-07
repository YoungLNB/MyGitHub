package com.vince.json;

import java.util.ArrayList;

public class Message {
    private long id;
    private String text;
    private ArrayList<Double> geo;
    private User user;

    public Message() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<Double> getGeo() {
        return geo;
    }

    public void setGeo(ArrayList<Double> arrayList) {
        this.geo = arrayList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", arrayList=" + geo +
                ", user=" + user +
                '}';
    }

}
