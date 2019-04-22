package cn.jianchengwang.todo.lib.netty.example.codec.serialization;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class SerializationClient {

    public static void main(String[] args) throws Exception{
        new SerializationClient("localhost", 8082).run();
    }

    private final String host;
    private final int port;

    public SerializationClient(String host, int port){
        this.host = host;
        this.port = port;
    }

    public void run() throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap  = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new SerializationClientHandlerInitializer());

            Channel channel = bootstrap.connect(host, port).sync().channel();

            SerializationBean user = new SerializationBean();

            for (int i = 0; i < 100; i++) {
                user = new SerializationBean();
                user.setAge(i);
                user.setName("wjc");
                channel.write(user);
            }
            channel.flush();

            // 等待连接关闭
            channel.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

    }
}
