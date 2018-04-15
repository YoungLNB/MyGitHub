package com.vince.mina;


import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    public static void main(String[] args) {
        //创建一个非阻塞的Server端Socket    NIO
        SocketAcceptor Acceptor = new NioSocketAcceptor();
        DefaultIoFilterChainBuilder Chain = Acceptor.getFilterChain();
//        //设定一个过滤器，一行一行的读取数据（/r/n）
//        Chain.addLast("my filterChain",new ProtocolCodecFilter(new TextLineCodecFactory()));
        //设定过滤器以对象为单位读取数据
        Chain.addLast("Object filter",new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        //设置服务器端的消息处理器
        Acceptor.setHandler(new MinaServerHandler());
        int port = 6666;  //服务器的端口号
        try {
            //绑定端口，同时启动服务器（不会阻塞，会立即返回）
            Acceptor.bind(new InetSocketAddress(port));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Mina Server running,listening:"+port);

    }
}
