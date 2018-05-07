package com.vince.json;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;

public class JSONDemo3 {
    //将一组JSON对象转换成一个JAVA对象集合，将一个JAVA对象集合转换成一组JSON对象
    @Test
    public void createJSON3(){
        Gson gson = new Gson();
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/vince/json/names.json");
        InputStreamReader is = new InputStreamReader(in);
        TypeToken<List<Name>> listTypeToken = new TypeToken<>(){};
        List<Name> names= gson.fromJson(is, listTypeToken.getType());
        System.out.println(names);

        String json = gson.toJson(names, listTypeToken.getType());
        System.out.println(json);
    }
    //将一个JSON对象转换成一个JAVA对象  将一个JAVA对象转换成一个JSON对象
    @Test
    public void createJSON2(){
        Gson gson = new Gson();
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/vince/json/name.json");
        InputStreamReader is = new InputStreamReader(in);
        Name name = gson.fromJson(is, Name.class);
        System.out.println(name);

        String json = gson.toJson(name);
        System.out.println(json);
    }
    @Test
    public void createJSON1(){
        ArrayList<Name> names = new ArrayList<>();
        names.add(new Name("li","ning","1450595121@qq.com"));
        names.add(new Name("kobe","bryant","11112222@163.com"));
        JsonArray jsonElements = new JsonArray();
        for (Name n :
                names) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("firstName",n.getFirstName());
            jsonObject.addProperty("lastName",n.getLastName());
            jsonObject.addProperty("email",n.getEmail());
            jsonElements.add(jsonObject);
        }
        System.out.println(jsonElements.toString());
    }
}
