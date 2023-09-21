package d10.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8888);
        Socket conn = ss.accept();
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        String s = br.readLine();
        while (s != null) {
            System.out.println("received: " + s);
            bw.write("hello " + s);
            bw.newLine();
            bw.flush();
            s = br.readLine();
        }
        br.close();
        bw.close();
        conn.close();
        ss.close();
    }
}
