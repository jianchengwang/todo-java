package cn.jianchengwang.todo.core.nio.example;

import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class PipeExample {

    public static void main(String[] args) {

        try {

            Pipe pipe = Pipe.open();

            // To write to a Pipe you need to access the sink channel. Here is how that is done:
            Pipe.SinkChannel sinkChannel = pipe.sink();
            // write to pipe
            String newData = "New String to write to file..." + System.currentTimeMillis();
            ByteBuffer buf = ByteBuffer.allocate(48);
            buf.clear();
            buf.put(newData.getBytes());
            buf.flip();
            while(buf.hasRemaining()) {
                sinkChannel.write(buf);
            }

            // To read from a Pipe you need to access the source channel. Here is how that is done:
            // read from pipe
            Pipe.SourceChannel sourceChannel = pipe.source();
            ByteBuffer buf1 = ByteBuffer.allocate(48);

            int bytesRead = sourceChannel.read(buf1);


        } catch (Exception e) {

            e.printStackTrace();
        }




    }
}
