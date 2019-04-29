package cn.jianchengwang.todo.lib.netty.todo.web.server.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;

import java.awt.*;
import java.util.Map;

import static io.netty.handler.codec.http.HttpHeaderNames.*;
import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.CLOSE;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private static final byte[] SUCCESS = { 'O', 'k' };

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {

        System.out.println("uri:" + msg.uri());
        System.out.println("method:" + msg.method().name());
        System.out.println("headers:");msg.headers().forEach(h -> System.out.println(h));

        System.out.println("params:");
        Map<String, String> paramMap = new RequestParser(msg).parse(); // 将GET, POST所有请求参数转换成Map对象
        paramMap.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });

        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(SUCCESS));
        response.headers().set(CONTENT_TYPE, "text/plain");
        response.headers().setInt(CONTENT_LENGTH, response.content().readableBytes());

        // Tell the client we're going to close connection
        response.headers().set(CONNECTION, CLOSE);

        ChannelFuture f = ctx.writeAndFlush(response);
        f.addListener(ChannelFutureListener.CLOSE);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
