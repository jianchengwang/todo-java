package todo.lib.netty.example.simple.withoutnetty;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private static final int MAX_DATA_LENGTH = 1024;
    private final Socket client;

    public ClientHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {

        System.out.println("new client join");

        try {

            InputStream in = client.getInputStream();
            while (true) {

                byte[] data = new byte[MAX_DATA_LENGTH];
                int len;

                while ((len = in.read(data)) != -1) {
                    String message = new String(data, 0, len);
                    System.out.println("receive client message: " + message);
                    client.getOutputStream().write(data);
                }
            }


        } catch (IOException e) {

            e.printStackTrace();

        }

    }
}
