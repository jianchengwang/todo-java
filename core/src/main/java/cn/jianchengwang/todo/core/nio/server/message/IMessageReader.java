package cn.jianchengwang.todo.core.nio.server.message;

import cn.jianchengwang.todo.core.nio.server.socket.Socket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

public interface IMessageReader {

    public void init(MessageBuffer readMessageBuffer);

    public void read(Socket socket, ByteBuffer byteBuffer) throws IOException;

    public List<Message> getMessages();
}
