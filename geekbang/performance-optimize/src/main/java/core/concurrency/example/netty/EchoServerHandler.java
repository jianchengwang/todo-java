package core.concurrency.example.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author jianchengwang
 * @date 2023/5/4
 */
class EchoServerHandler extends
        ChannelInboundHandlerAdapter {
    //处理读事件
    @Override
    public void channelRead(
            ChannelHandlerContext ctx, Object msg){
        ctx.write(msg);
    }
    //处理读完成事件
    @Override
    public void channelReadComplete(
            ChannelHandlerContext ctx){
        ctx.flush();
    }
    //处理异常事件
    @Override
    public void exceptionCaught(
            ChannelHandlerContext ctx,  Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
