package todo.core.network.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

// 单线程的服务
public class SingleThreadedServer implements Runnable {

    protected int          serverPort   = 8080;
    protected ServerSocket serverSocket = null;
    protected boolean      isStopped    = false;
    protected Thread       runningThread= null;

    public SingleThreadedServer(int port){
        this.serverPort = port;
    }

    public static void main(String[] args) {

        SingleThreadedServer server = new SingleThreadedServer(9000);
        new Thread(server).start();

        try {
            Thread.sleep(20 * 1000);

            // u can input 'curl http://127.0.0.1:9000' on the terminal to simulation clientSocket request

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Stopping Server");
        server.stop();

    }

    @Override
    public void run() {

        synchronized (this) {
            this.runningThread = Thread.currentThread();
        }

        openServerSocket();

        while (!isStopped()) {

            Socket clientSocket = null;

            try {
                clientSocket = serverSocket.accept();

            } catch (IOException e) {

                if(isStopped()) {
                    System.out.println("Server Stopped.") ;
                    return;
                }

                throw new RuntimeException(
                        "Error accepting client connection", e);
            }

            try {
                processClientRequest(clientSocket);
            } catch (Exception e) {
                //log exception and go on to next request.
            }

        }

        System.out.println("Server Stopped.");

    }

    private void processClientRequest(Socket clientSocket)
            throws Exception {
        InputStream input  = clientSocket.getInputStream();
        OutputStream output = clientSocket.getOutputStream();
        long time = System.currentTimeMillis();

        byte[] responseDocument = ("<html><body>" +
                "Singlethreaded Server: " +
                time +
                "</body></html>").getBytes("UTF-8");

        byte[] responseHeader =
                ("HTTP/1.1 200 OK\r\n" +
                        "Content-Type: text/html; charset=UTF-8\r\n" +
                        "Content-Length: " + responseDocument.length +
                        "\r\n\r\n").getBytes("UTF-8");

        output.write(responseHeader);
        output.write(responseDocument);
        output.close();
        input.close();
        System.out.println("Request processed: " + time);
    }

    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop(){
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port " + this.serverPort, e);
        }
    }
}
