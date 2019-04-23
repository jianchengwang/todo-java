package cn.jianchengwang.todo.lib.netty.example.file;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import io.netty.util.CharsetUtil;

public class FileServer {

    static final boolean SSL = System.getProperty("ssl") != null;
    // Use the same default port with the telnet example so that we can use the telnet client example to access it.
    static final int PORT = Integer.parseInt(System.getProperty("port", SSL? "8992" : "8082"));

    public static void main(String[] args) throws Exception {

        // Configure ssl
        final SslContext sslCtx;

        if(SSL) {
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
        } else {
            sslCtx = null;
        }

        // Configure the server
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
            .channel(NioServerSocketChannel.class)
            .option(ChannelOption.SO_BACKLOG, 100)
            .handler(new LoggingHandler(LogLevel.INFO))
            .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {

                    ChannelPipeline p = ch.pipeline();
                    if(sslCtx != null) {
                        p.addLast(sslCtx.newHandler(ch.alloc()));
                    }
                    p.addLast(
                            new StringEncoder(CharsetUtil.UTF_8),
                            new LineBasedFrameDecoder(8192),
                            new StringDecoder(CharsetUtil.UTF_8),
                            new FileServerHandler()
                    );

                }
            });

            // Statr the server
            ChannelFuture f = b.bind(PORT).sync();

            // Wait until the server is closed
            f.channel().closeFuture().sync();


        } finally {

            // Shut down all event loops to terminate all threads
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
