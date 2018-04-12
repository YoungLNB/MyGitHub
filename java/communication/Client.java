package com.vince.communication;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {
            Socket socket = new Socket("localhost", 6666);
            System.out.println("服务器连接成功");
            //oos和ois的顺序不能调换
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //向服务器发送登录消息
            System.out.println("请输入名称:");
            String name = input.nextLine();
            Message message = new Message(name, null, null, MessagrType.Type_Login);
            oos.writeObject(message);
            message = (Message) ois.readObject();
            System.out.println(message.getInfo() + message.getFromname());
            //启动读取消息的线程
            executorService.execute(new ReaderThread(ois));
            //使用主线程来实现发送消息
            boolean flag = true;
            while (flag) {
                message = new Message();
                System.out.println("To:");
                message.setToname(input.nextLine());
                message.setFromname(name);
                message.setType(MessagrType.Type_Send);
                System.out.println("Info:");
                message.setInfo(input.nextLine());
                oos.writeObject(message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

//读取消息
class ReaderThread implements Runnable {
    private ObjectInputStream in;
    private boolean flag = true;

    public ReaderThread(ObjectInputStream in) {
        this.in = in;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        try {
            while (flag) {
                Message message = (Message) in.readObject();
                System.out.println("[" + message.getFromname() + "]" + "对我说:" + message.getInfo());
            }
            if (in != null) {
                in.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
