package todo.java.geekbang.designpattern.structural.bridge;

import java.util.List;

/**
 * @author jianchengwang
 * @date 2023/6/14
 */
public class TelephoneMsgSender implements MsgSender {

    private List telephones;
    public TelephoneMsgSender(List telephones) {
        this.telephones = telephones;
    }

    @Override
    public void send(String message) {

    }
}
