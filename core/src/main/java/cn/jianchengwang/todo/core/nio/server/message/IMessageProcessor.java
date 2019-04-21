package cn.jianchengwang.todo.core.nio.server.message;

public interface IMessageProcessor {

    void process(Message message, WriteProxy writeProxy);

}
