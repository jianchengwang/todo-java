package cn.jianchengwang.todo.core.io;

import java.io.*;

public class SystemInOutErr {

    public static void main(String[] args) {
        try {
            InputStream input = new FileInputStream("c:\\data\\...");
            System.out.println("File opened...");

            OutputStream output = new FileOutputStream("c:\\data\\system.out.txt");
            PrintStream printOut = new PrintStream(output);

            System.setOut(printOut);

        } catch (IOException e){
            System.err.println("File opening failed:");
            e.printStackTrace();
        }
    }
}
