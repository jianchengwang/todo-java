package cn.jianchengwang.todo.core.nio.example;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

public class ChannelExample {

    public static void main(String[] args) {

//        buffer();

//        scatterAndGather();

        transfer();

    }

    public static  void buffer() {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(ChannelExample.class.getResource("/data.txt").getPath(), "rw");
        ) {

            FileChannel fileChannel = randomAccessFile.getChannel();

            //create buffer with capacity of 48 bytes
            // Buffer have 3 properties
            // Capacity
            //Being a memory block, a Buffer has a certain fixed size, also called its "capacity".
            // You can only write capacity bytes, longs, chars etc. into the Buffer.
            // Once the Buffer is full,
            // you need to empty it (read the data, or clear it) before you can write more data into it.
            //
            //Position
            //When you write data into the Buffer, you do so at a certain position.
            // Initially the position is 0. When a byte, long etc.
            // has been written into the Buffer the position is advanced to point to the next cell in the buffer to insert data into.
            // Position can maximally become capacity - 1.
            //
            //When you read data from a Buffer you also do so from a given position.
            // When you flip a Buffer from writing mode to reading mode, the position is reset back to 0.
            // As you read data from the Buffer you do so from position, and position is advanced to next position to read.
            //
            //Limit
            //In write mode the limit of a Buffer is the limit of how much data you can write into the buffer.
            // In write mode the limit is equal to the capacity of the Buffer.
            //
            //When flipping the Buffer into read mode, limit means the limit of how much data you can read from the data.
            // Therefore, when flipping a Buffer into read mode, limit is set to write position of the write mode.
            // In other words, you can read as many bytes as were written (limit is set to the number of bytes written, which is marked by position).
            ByteBuffer buffer = ByteBuffer.allocate(48);

            // channel write data into buffer
            int byteRead = fileChannel.read(buffer);
            while (byteRead != -1) {

                // make buffer ready to read
                buffer.flip();

                while (buffer.hasRemaining()) {

                    // read one byte a time
                    System.out.println((char) buffer.get());
                }

                // make buffer ready to write, u can also use buffer.compact()
                // The clear() method clears the whole buffer.
                // The compact() method only clears the data which you have already read.
                // Any unread data is moved to the beginning of the buffer, and data will now be written into the buffer after the unread data
                buffer.clear();

                // continue write into buffer
                byteRead = fileChannel.read(buffer);
            }


        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static void scatterAndGather() {

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(ChannelExample.class.getResource("/data.txt").getPath(), "rw");
        ) {
            FileChannel channel = randomAccessFile.getChannel();

            ByteBuffer header = ByteBuffer.allocate(128);
            ByteBuffer body   = ByteBuffer.allocate(1024);

            ByteBuffer[] bufferArray = { header, body };

            channel.read(bufferArray);

            // channel.write(bufferArray);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static void transfer() {

        try (RandomAccessFile fromFile = new RandomAccessFile(ChannelExample.class.getResource("/fromFile.txt").getPath(), "rw");
             RandomAccessFile toFile = new RandomAccessFile(ChannelExample.class.getResource("/toFile.txt").getPath(), "rw");
        ) {

            FileChannel      fromChannel = fromFile.getChannel();

            FileChannel      toChannel = toFile.getChannel();

            long position = 0;
            long count    = fromChannel.size();

            toChannel.transferFrom(fromChannel, position, count);

            fromChannel.transferTo(position, count, toChannel);

            fromChannel.close();

            toChannel.close();

        } catch (Exception e) {

            e.printStackTrace();

        }


    }

    public static void socketChannel() {

        try {
            // open
            SocketChannel socketChannel = SocketChannel.open();

            // use none-blocking mode
            socketChannel.configureBlocking(false);

            // connect
            socketChannel.connect(new InetSocketAddress("http://jenkov.com", 80));

            // read
            ByteBuffer buf = ByteBuffer.allocate(48);
            int bytesRead = socketChannel.read(buf);

            // write
            String newData = "New String to write to file..." + System.currentTimeMillis();
            ByteBuffer buf1 = ByteBuffer.allocate(48);
            buf.clear();
            buf.put(newData.getBytes());
            buf.flip();
            while(buf.hasRemaining()) {
                socketChannel.write(buf);
            }

            // close
            socketChannel.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public static void serverSocketChannel() {

        try {

            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

            serverSocketChannel.socket().bind(new InetSocketAddress(9999));

            serverSocketChannel.configureBlocking(false);

            while(true){
                SocketChannel socketChannel =
                        serverSocketChannel.accept();

                if(socketChannel!=null) {
                    //do something with socketChannel...

                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }


    }

    public static void datagramChannel() {

        try {

            DatagramChannel channel = DatagramChannel.open();
            channel.socket().bind(new InetSocketAddress(9999));

            // receive data
            ByteBuffer buf = ByteBuffer.allocate(48);
            buf.clear();
            channel.receive(buf);

            // send data
            String newData = "New String to write to file..."
                    + System.currentTimeMillis();

            ByteBuffer buf1 = ByteBuffer.allocate(48);
            buf1.clear();
            buf1.put(newData.getBytes());
            buf1.flip();
            int bytesSent = channel.send(buf1, new InetSocketAddress("jenkov.com", 80));

            // read and write
            int bytesRead = channel.read(buf);
            int bytesWritten = channel.write(buf);


        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static void asynchronousFileChannel() {

        try {

            Path path = Paths.get(ChannelExample.class.getResource("/data.txt").getPath());

            AsynchronousFileChannel fileChannel1 =
                    AsynchronousFileChannel.open(path, StandardOpenOption.READ);

            // read via Future
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            long position = 0;

            Future<Integer> operation = fileChannel1.read(buffer, position);
            while(!operation.isDone());
            buffer.flip();
            byte[] data = new byte[buffer.limit()];
            buffer.get(data);
            System.out.println(new String(data));
            buffer.clear();

            // read via CompletionHandler
            fileChannel1.read(buffer, position, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    System.out.println("result = " + result);

                    attachment.flip();
                    byte[] data = new byte[attachment.limit()];
                    attachment.get(data);
                    System.out.println(new String(data));
                    attachment.clear();
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {

                }
            });

            // write via Future
            AsynchronousFileChannel fileChannel2 =
                    AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);

            ByteBuffer buffer2 = ByteBuffer.allocate(1024);
            long position2 = 0;
            buffer.put("test data".getBytes());
            buffer.flip();

            Future<Integer> operation2 = fileChannel2.write(buffer, position);
            buffer.clear();

            while(!operation.isDone());

            System.out.println("Write done");

            // write via CompletionHandler
            fileChannel2.write(buffer, position, buffer, new CompletionHandler<Integer, ByteBuffer>() {

                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    System.out.println("bytes written: " + result);
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    System.out.println("Write failed");
                    exc.printStackTrace();
                }
            });


        } catch (Exception e) {

            e.printStackTrace();
        }


    }


}
