package com.vince.mina;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        //创建连接
        NioSocketConnector connector = new NioSocketConnector();
        DefaultIoFilterChainBuilder Chain = connector.getFilterChain();
//        Chain.addLast("my Chain",new ProtocolCodecFilter(new TextLineCodecFactory()));
        //设定过滤器以对象为单位读取数据
        Chain.addLast("Object filer",new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        connector.setHandler( new MinaClientHandler());
        connector.setConnectTimeoutMillis(10000);
        //连接服务器
        ConnectFuture cf = connector.connect(new InetSocketAddress("localhost",6666));
        cf.awaitUninterruptibly();//等待连接成功
        Scanner input = new Scanner(System.in);

        while(true){
//            System.out.println("请输入:");
//            String info = input.nextLine();
//            //发送消息
//            cf.getSession().write(info);

            //以对象的形式传输数据
            Message message = new Message();
            System.out.println("from:");
            message.setFrom(input.nextLine());
            System.out.println("to:");
            message.setTo(input.nextLine());
            System.out.println("info:");
            message.setInfo(input.nextLine());
            message.setType("send");
            cf.getSession().write(message);
        }
        //等待服务器关闭，结束长连接
//        cf.getSession().getCloseFuture().awaitUninterruptibly();
//        connector.dispose(); //关闭连接
    }
}
