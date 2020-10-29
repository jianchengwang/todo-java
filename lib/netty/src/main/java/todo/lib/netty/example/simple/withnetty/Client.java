package todo.lib.netty.example.simple.withnetty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class Client {

    public static void main(String[] args) throws InterruptedException {

        // Creating an EventLoopGroup
        EventLoopGroup group = new NioEventLoopGroup();

        try {

            // Creating and Configuring a Bootstrap
            Bootstrap clientBootstrap = new Bootstrap();
            clientBootstrap.group(group);
            clientBootstrap.channel(NioSocketChannel.class);
            clientBootstrap.remoteAddress(new InetSocketAddress("localhost", 9000));

            // Creating a ChannelInitializer
            clientBootstrap.handler(new ChannelInitializer<SocketChannel>() {


                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ClientHandler());
                }
            });

            // Starting the Client
            ChannelFuture channelFuture = clientBootstrap.connect().sync();
            channelFuture.channel().closeFuture().sync();


        } finally {

            group.shutdownGracefully().sync();

        }



    }
}
