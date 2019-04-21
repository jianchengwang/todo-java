package cn.jianchengwang.todo.lib.netty.example.simple.withoutnetty;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{

    private ServerSocket serverSocket;

    public Server(int port) {

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("simple running success in port:" + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("simple running failed");
        }

    }

    @Override
    public void run() {
        while (true) {

            try {
                Socket client = serverSocket.accept();
                new Thread(new ClientHandler(client)).start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
