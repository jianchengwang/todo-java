package core.concurrency.design.threadpermessage;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author jianchengwang
 * @date 2023/4/28
 */
public class Test {
    public static void main(String[] args) throws IOException {

        final ServerSocketChannel ssc  =
        ServerSocketChannel.open().bind(
                new InetSocketAddress(8080));
        //处理请求
        try {
            while (true) {
                // 接收请求
                SocketChannel sc = ssc.accept();
                // 每个请求都创建一个线程
                new Thread(()->{
                    try {
                        // 读Socket
                        ByteBuffer rb = ByteBuffer
                                .allocateDirect(1024);
                        sc.read(rb);
                        //模拟处理请求
                        Thread.sleep(2000);
                        // 写Socket
                        ByteBuffer wb =
                                (ByteBuffer)rb.flip();
                        sc.write(wb);
                        // 关闭Socket
                        sc.close();
                    }catch(IOException e){
                        throw new UncheckedIOException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }
        } finally {
            ssc.close();
        }
    }
}
