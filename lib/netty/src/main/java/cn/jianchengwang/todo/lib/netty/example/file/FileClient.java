package cn.jianchengwang.todo.lib.netty.example.file;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FileClient {

    private String host;
    private int port;
    private String dest; // receive file dir

    public FileClient(String host, int port, String dest) {
        this.host = host;
        this.port = port;
        this.dest = dest;
    }

    public void run() throws Exception{

        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup)
             .channel(NioSocketChannel.class)
             .option(ChannelOption.SO_KEEPALIVE, true)
             .handler(new ChannelInitializer<SocketChannel>() {
                 @Override
                 protected void initChannel(SocketChannel ch) {

                     ChannelPipeline p = ch.pipeline();
                     p.addLast(
                             new StringEncoder(CharsetUtil.UTF_8),
                             new StringDecoder(CharsetUtil.UTF_8),
                             new FileClientHandler(dest)
                     );
                 }
             });

            ChannelFuture f = b.connect(host, port).sync();
            Channel channel = f.channel();

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String readLine = in.readLine();
                channel.writeAndFlush(readLine + "\r\n");
            }

//            f.channel().closeFuture().sync();
        } finally {

            workerGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws Exception {

       new FileClient("localhost", 8082, "/home/wjc/ext/workspace/tmp/test").run();

    }

}
