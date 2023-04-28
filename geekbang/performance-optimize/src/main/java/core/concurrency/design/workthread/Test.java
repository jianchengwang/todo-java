package core.concurrency.design.workthread;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.*;

/**
 * @author jianchengwang
 * @date 2023/4/28
 */
public class Test {
    public static void main(String[] args) throws IOException {

        ExecutorService es = new ThreadPoolExecutor(
                50, 500,
                60L, TimeUnit.SECONDS,
                //注意要创建有界队列
                new LinkedBlockingQueue<Runnable>(2000),
                //建议根据业务需求实现ThreadFactory
                r->{
                    return new Thread(r, "echo-"+ r.hashCode());
                },
                //建议根据业务需求实现RejectedExecutionHandler
                new ThreadPoolExecutor.CallerRunsPolicy());;
        final ServerSocketChannel ssc =
                ServerSocketChannel.open().bind(
                        new InetSocketAddress(8080));
        //处理请求
        try {
            while (true) {
                // 接收请求
                SocketChannel sc = ssc.accept();
                // 将请求处理任务提交给线程池
                es.execute(()->{
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
                });
            }
        } finally {
            ssc.close();
            es.shutdown();
        }
    }
}
