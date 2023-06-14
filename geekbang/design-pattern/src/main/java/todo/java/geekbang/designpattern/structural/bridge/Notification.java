package todo.java.geekbang.designpattern.structural.bridge;

/**
 * @author jianchengwang
 * @date 2023/6/14
 */
public abstract class Notification {
    protected MsgSender msgSender;
    public Notification(MsgSender msgSender) {
        this.msgSender = msgSender;
    }

    public abstract void notify(String message);
}
