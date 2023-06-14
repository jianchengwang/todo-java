package todo.java.geekbang.designpattern.structural.bridge;

/**
 * @author jianchengwang
 * @date 2023/6/14
 */
public class UrgencyNotification extends Notification {

    public UrgencyNotification(MsgSender msgSender) {
        super(msgSender);
    }

    @Override
    public void notify(String message) {
        msgSender.send(message);
    }
}
