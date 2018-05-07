package com.vince.json;

import com.google.gson.stream.JsonReader;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class JSONDeom1 {
    /**
     * 解析一个JSON数组
     */
    @Test
    public void JSONParseName(){
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/vince/json/names.json");
        InputStreamReader isr = new InputStreamReader(inputStream);
        JsonReader jsonReader = new JsonReader(isr);
        ArrayList<Name> names = new ArrayList<>();

        try {
            //开始解析JSON数组,
            jsonReader.beginArray();  //类似于指针一样的，不断往下指
            while(jsonReader.hasNext()){
                names.add(ParseName(jsonReader));
            }
            jsonReader.endArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(names.toArray()));
    }
    //此方法用来解析对象name
    private Name ParseName(JsonReader jsonReader){
        Name name = null;
        try {
            jsonReader.beginObject();
            name = new Name();
            while(jsonReader.hasNext()){
                String s = jsonReader.nextName();
                if("firstName".equals(s)){
                    name.setFirstName(jsonReader.nextString());
                }else if("lastName".equals(s)){
                    name.setLastName(jsonReader.nextString());
                }else if("email".equals(s)){
                    name.setEmail(jsonReader.nextString());
                }
            }
            //结束解析
            jsonReader.endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }
}
