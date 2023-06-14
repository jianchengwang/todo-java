package todo.java.geekbang.designpattern.structural.bridge;

import java.util.List;

/**
 * @author jianchengwang
 * @date 2023/6/14
 */
public class WechatMsgSender implements MsgSender {

    private List<String> wechatIds;
    public WechatMsgSender(List<String> wechatIds) {
        this.wechatIds = wechatIds;
    }

    @Override
    public void send(String message) {

    }
}
