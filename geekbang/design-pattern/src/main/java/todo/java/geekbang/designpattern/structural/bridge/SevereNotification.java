package todo.java.geekbang.designpattern.structural.bridge;

/**
 * @author jianchengwang
 * @date 2023/6/14
 */
public class SevereNotification extends Notification {

    public SevereNotification(MsgSender msgSender) {
        super(msgSender);
    }

    @Override
    public void notify(String message) {
        msgSender.send(message);
    }
}
