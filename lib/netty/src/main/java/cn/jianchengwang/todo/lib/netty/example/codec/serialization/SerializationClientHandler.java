package cn.jianchengwang.todo.lib.netty.example.codec.serialization;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SerializationClientHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object obj) throws Exception {

        if (obj instanceof SerializationBean) {
            SerializationBean user = (SerializationBean) obj;
            System.out.println("Client get msg form Server - name:"
                    + user.getName() + ";age:" + user.getAge());
        }
    }
}
