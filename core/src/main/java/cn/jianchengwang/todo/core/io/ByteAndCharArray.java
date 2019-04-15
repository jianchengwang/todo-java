package cn.jianchengwang.todo.core.io;

import java.io.ByteArrayInputStream;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStream;

public class ByteAndCharArray {

    public static void main(String[] args) throws IOException {

        readByteArray();

        writeCharArray();

    }

    public static void readByteArray() throws IOException {
        byte[] bytes = new byte[255];

        //write data into byte array...
        for(Integer i=0; i<255; i++) {
            bytes[i] = i.byteValue();
        }

        InputStream input = new ByteArrayInputStream(bytes);

        //read first byte
        int data = input.read();
        while(data != -1) {
            //do something with data

            //read next byte
            data = input.read();

            System.out.println(data);
        }
    }

    public static void writeCharArray() throws IOException {

        CharArrayWriter writer = new CharArrayWriter();

        writer.write("This text is converted to bytes".toCharArray());

        char[] chars = writer.toCharArray();

        for(char i: chars) {
            System.out.print(i);
        }

    }
}
