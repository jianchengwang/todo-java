package cn.jianchengwang.todo.core.network.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPooledServer implements Runnable {

    protected int          serverPort   = 8080;
    protected ServerSocket serverSocket = null;
    protected boolean      isStopped    = false;
    protected Thread       runningThread= null;
    protected ExecutorService threadPool =
            Executors.newFixedThreadPool(10);

    public ThreadPooledServer(int port){
        this.serverPort = port;
    }

    class WorkerRunnable implements Runnable {

        protected Socket clientSocket = null;
        protected String serverText   = null;

        public WorkerRunnable(Socket clientSocket, String serverText) {
            this.clientSocket = clientSocket;
            this.serverText   = serverText;
        }

        public void run() {
            try {
                InputStream input  = clientSocket.getInputStream();
                OutputStream output = clientSocket.getOutputStream();
                long time = System.currentTimeMillis();
                output.write(("HTTP/1.1 200 OK\n\nWorkerRunnable: " +
                        this.serverText + " - " +
                        time +
                        "" + " currentThread:" + Thread.currentThread().getName()).getBytes());
                output.close();
                input.close();
                System.out.println("Request processed: " + time);
            } catch (IOException e) {
                //report exception somewhere.
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        ThreadPooledServer server = new ThreadPooledServer(9000);
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

            threadPool.execute(new WorkerRunnable(clientSocket, "Thread Pooled Server"));
        }

        System.out.println("Server Stopped.");

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
