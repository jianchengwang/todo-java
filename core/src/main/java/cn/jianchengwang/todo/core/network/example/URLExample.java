package cn.jianchengwang.todo.core.network.example;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLExample {

    public static void main(String[] args) {

        try {
            URL url = new URL("http://jenkov.com");

            URLConnection urlConnection = url.openConnection();

            // default get request, but if setDoOutput(true), post request.
            // Using this OutputStream you can write any data you want in the body of the HTTP request.
            // Remember to URL encode it (search Google for an explanation of URL encoding).
            urlConnection.setDoOutput(true); OutputStream output = urlConnection.getOutputStream();


            InputStream in = urlConnection.getInputStream();

            int data = in.read();
            while (data != -1) {
                System.out.print((char) data);
                data = in.read();
            }

            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
