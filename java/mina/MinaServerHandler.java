package com.vince.mina;
/**
 * 服务器端的消息处理器
 */

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;


public class MinaServerHandler extends IoHandlerAdapter {
    //打开一次会话
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
        System.out.println("welcome client:"+session.getRemoteAddress());
    }
    //关闭一次会话
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
        System.out.println("client closed");
    }
    //接收消息的方法
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        super.messageReceived(session, message);
//        String meg = (String) message;  //接受到的消息
        Message meg = (Message) message;
        System.out.println("收到客户端发来的消息:"+meg);
        meg.setInfo("打篮球");
//        session.write("echo："+meg);  //向客户端发送消息对象
        session.write(meg);
    }


}
