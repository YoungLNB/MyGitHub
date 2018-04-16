package com.vince.aop;

import java.util.ArrayList;

public class IMangerImpl implements IManger {
    private ArrayList<String> list = new ArrayList<String>();
    @Override
    public void add(String item) {
        list.add(item);
        System.out.println(list.size());
    }
}
