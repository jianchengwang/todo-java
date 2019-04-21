package cn.jianchengwang.todo.lib.netty.example.simple.withoutnetty;

import java.io.IOException;
import java.net.Socket;

public class Client {

    private static final String HOST = "127.0.0.1";
    private static final int port = 9000;
    private static final int SLEEP_TIME = 500;

    public static void main(String[] args) throws IOException {

        final Socket client = new Socket(HOST, port);

        new Thread(() -> {

            System.out.println("client boot success...");
            while (true) {
                try {
                    String message = "hello world";
                    System.out.println("client send message:" + message);
                    client.getOutputStream().write(message.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(SLEEP_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

    }
}
