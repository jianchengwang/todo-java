package info.jianchengwang.todo.springboot2.si.mq.rabbitmq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wjc
 * @date 2021/1/22
 */
@EnableRabbit
@SpringBootApplication
public class SiMqRabbitmqMain {
    public static void main(String[] args) {
        SpringApplication.run(SiMqRabbitmqMain.class, args);
    }
}
