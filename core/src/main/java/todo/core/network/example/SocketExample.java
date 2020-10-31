package todo.core.network.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

public class SocketExample {

    public static void main(String[] args) {

        socketExample();

        serverSocketTCP();

        datagrameSocketUDP();

    }


    public static void socketExample() {

        try {
            Socket socket = new Socket("78.46.84.171", 80);
            Socket socket2 = new Socket("jenkov.com", 80);

            // write to socket
            OutputStream out = socket.getOutputStream();
            out.write("some data".getBytes());
            out.flush();
            out.close();

            // read from socket
            InputStream in = socket.getInputStream();
            int data = in.read();
            // read more data
            in.close();

            // close socket
            socket.close();



        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void serverSocketTCP() {

        boolean isStopped = false;
        try (ServerSocket serverSocket = new ServerSocket(9000)) {

            while (!isStopped) {
                Socket socket = serverSocket.accept();

                // do something

                // close socket
                socket.close();

                // close serverSocket
                serverSocket.close();
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static void datagrameSocketUDP() {

        try {

            // send
            // create socket
            DatagramSocket datagramSocket = new DatagramSocket();

            // create packet
            byte[] buffer = new byte[65508];
            InetAddress address = InetAddress.getByName("jenkov.com");
            DatagramPacket packet = new DatagramPacket(
                    buffer, buffer.length, address, 9000);
            // send packet
            datagramSocket.send(packet);

            // receive
            DatagramSocket datagramSocket1 = new DatagramSocket(80);
            byte[] buffer1 = new byte[10];
            DatagramPacket packet1 = new DatagramPacket(buffer1, buffer1.length);
            datagramSocket.receive(packet);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
