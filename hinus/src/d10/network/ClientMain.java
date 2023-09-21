package d10.network;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class ClientMain {
    public static void main(String[] args) throws IOException {
        Socket conn = new Socket("localhost", 8888);
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        String s = stdIn.readLine();
        while (s != null) {
            System.out.println("sending: " + s);
            bw.write(s + "\n");
            bw.flush();
            String out = br.readLine();
            System.out.println("received:" + out);
            s = stdIn.readLine();
        }
        stdIn.close();
        br.close();
        bw.close();
        conn.close();
    }
}
