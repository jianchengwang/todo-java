package cn.jianchengwang.todo.lib.netty.example.codec.jackcon;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.io.InputStream;
import java.util.List;

// 解码器
public class JacksonDecoder<T> extends ByteToMessageDecoder {

    private final Class<T> clazz;

    public JacksonDecoder(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        InputStream byteBufInputStream = new ByteBufInputStream(in);
        out.add(JacksonMapper.getInstance().readValue(byteBufInputStream, clazz));

    }
}
