package todo.java.geekbang.designpattern.structural.bridge;

import java.util.List;

/**
 * @author jianchengwang
 * @date 2023/6/14
 */
public class EmailMsgSender implements MsgSender {
    private List<String> emailAddresses;
    public EmailMsgSender(List<String> emailAddresses) {
        this.emailAddresses = emailAddresses;
    }
    @Override
    public void send(String message) {

    }
}
