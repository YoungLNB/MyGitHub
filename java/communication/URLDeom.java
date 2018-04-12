package com.vince.communication;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class URLDeom {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87&step_word=&hs=0&pn=4&spn=0&di=184647646480&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=2239146502%2C165013516&os=13729796%2C1018751327&simid=4179302581%2C635478331&adpicid=0&lpn=0&ln=1982&fr=&fmq=1523377856526_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F018d4e554967920000019ae9df1533.jpg%40900w_1l_2o_100sh.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bzv55s_z%26e3Bv54_z%26e3BvgAzdH3Fo56hAzdH3FZNTQxNzQ9_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0&islist=&querylist=");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("E:\\图片\\ddd.jpg"));
            byte[] bytes = new byte[1024];
            int len = -1;
            while((len=bufferedInputStream.read(bytes))!=-1){
                bufferedOutputStream.write(bytes,0,len);
                bufferedOutputStream.flush();
            }
            bufferedOutputStream.close();
            bufferedInputStream.close();
            System.out.println("下载成功");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
