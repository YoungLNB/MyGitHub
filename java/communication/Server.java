package com.vince.communication;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 服务端
 */
public class Server {
    public static void main(String[] args) {
        //保存客户端处理的线程
        Vector<UserThread> vector = new Vector<>();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        try {
            //创建服务端的Socket
            ServerSocket serverSocket = new ServerSocket(6666);
            System.out.println("服务端已启动，等待客户端连接...");
            while (true) {
                Socket socket = serverSocket.accept();
                UserThread ut = new UserThread(socket,vector);
                executorService.execute(ut);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 客户端处理的线程
 */
class UserThread implements Runnable {
    private String name;//唯一标示客户端
    private Socket socket;
    private Vector<UserThread> vector;//客户端处理的线程集合
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private boolean flag = true; //标志变量

    public UserThread(Socket socket, Vector<UserThread> vector) {
        this.socket = socket;
        this.vector = vector;
        vector.add(this);
    }

    @Override
    public void run() {
        try {
            System.out.println("客户端" + socket.getInetAddress().getHostAddress() + "已经连接");
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            while (flag) {
                Message message = (Message) ois.readObject();
                int type = message.getType();
                switch (type) {
                    case MessagrType.Type_Login:
                        name = message.getFromname();
                        message.setInfo("欢迎你:");
                        oos.writeObject(message);
                        break;
                    case MessagrType.Type_Send:
                        String toname = message.getToname();
                        UserThread ut;
                        for (int i = 0; i < vector.size(); i++) {
                            ut = vector.get(i);
                            if (toname.equals(ut.name) && ut!=this ) {
                                ut.oos.writeObject(message);
                                break;
                            }
                        }
                        break;


                }
            }

            ois.close();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
