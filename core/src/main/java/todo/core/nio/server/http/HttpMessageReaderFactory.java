package todo.core.nio.server.http;

import todo.core.nio.server.message.IMessageReader;
import todo.core.nio.server.message.IMessageReaderFactory;

public class HttpMessageReaderFactory implements IMessageReaderFactory {
    @Override
    public IMessageReader createMessageReader() {
        return new HttpMessageReader();
    }
}
