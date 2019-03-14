package cn.jianchengwang.learnconcurrency.example.mq.kafka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cn.jianchengwang.learnconcurrency.example.mq.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author sss
 * @date 2019-02-20
 */
@Slf4j
public class KafkaSender {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    public void send(String msg) {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(msg);
        message.setSendTime(new Date());
        log.info("send message:{}", message);
        kafkaTemplate.send(TopicConstants.TEST, gson.toJson(message));
    }
}
