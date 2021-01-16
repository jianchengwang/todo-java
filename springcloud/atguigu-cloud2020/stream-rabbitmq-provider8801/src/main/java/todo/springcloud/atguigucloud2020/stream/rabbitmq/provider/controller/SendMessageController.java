package todo.springcloud.atguigucloud2020.stream.rabbitmq.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import todo.springcloud.atguigucloud2020.stream.rabbitmq.provider.service.IMessageProvider;

import javax.annotation.Resource;

/**
 * @author wjc
 * @date 2021/1/17
 */
@RestController
public class SendMessageController {
    @Resource
    private IMessageProvider messageProvider;

    @GetMapping(value = "/sendMessage")
    public String sendMessage() {
        return messageProvider.send();
    }

}