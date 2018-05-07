package com.vince.json;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class JSONDeom2 {
    @Test
    public void JSONParseMessage(){
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/vince/json/message.json");
        InputStreamReader in = new InputStreamReader(is);

        JsonReader jsonReader = new JsonReader(in);
        ArrayList<Message> messages = ParseMessageArrayList(jsonReader);
        for (Message m :
                messages) {
            System.out.println(m);
        }
    }
    //解析Message数组
    private ArrayList<Message>  ParseMessageArrayList(JsonReader jsonReader) {
        ArrayList<Message> messages = new ArrayList<>();
        try {
            jsonReader.beginArray();
            while(jsonReader.hasNext()){
                messages.add(ParseMessage(jsonReader));
            }
            jsonReader.endArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return messages;
    }

    //解析一个message对象
    private Message ParseMessage(JsonReader jsonReader){
        Message message = new Message();
        try {
            jsonReader.beginObject();
            while(jsonReader.hasNext()){
                String s = jsonReader.nextName();
                if("id".equals(s)){
                    message.setId(jsonReader.nextLong());
                }else if("text".equals(s)){
                    message.setText(jsonReader.nextString());
                }else if("geo".equals(s) && jsonReader.peek()!=JsonToken.NULL){
                    message.setGeo(readgeo(jsonReader));
                }else if("user".equals(s)){
                    message.setUser(readUser(jsonReader));
                }else{
                    jsonReader.skipValue();
                }
            }
            jsonReader.endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }


    //解析User对象
    private User readUser(JsonReader jsonReader){
        User user = new User();
        try {
            jsonReader.beginObject();
            while(jsonReader.hasNext()){
                String s = jsonReader.nextName();
                if("name".equals(s)){
                    user.setName(jsonReader.nextString());
                }else if("followers_count".equals(s)){
                    user.setFollowers_count(jsonReader.nextInt());
                }else {
                    jsonReader.skipValue();
                }
            }
            jsonReader.endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;

    }
    //解析Geo集合
    private ArrayList<Double> readgeo(JsonReader jsonReader){
        ArrayList<Double> doubles = new ArrayList<>();
        try {
            jsonReader.beginArray();
            while (jsonReader.hasNext()){
                doubles.add(jsonReader.nextDouble());
            }
            jsonReader.endArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doubles;
    }
}
