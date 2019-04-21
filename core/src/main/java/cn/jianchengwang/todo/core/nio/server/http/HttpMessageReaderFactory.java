package cn.jianchengwang.todo.core.nio.server.http;

import cn.jianchengwang.todo.core.nio.server.message.IMessageReader;
import cn.jianchengwang.todo.core.nio.server.message.IMessageReaderFactory;

public class HttpMessageReaderFactory implements IMessageReaderFactory {
    @Override
    public IMessageReader createMessageReader() {
        return new HttpMessageReader();
    }
}
