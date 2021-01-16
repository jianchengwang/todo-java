package todo.springcloud.atguigucloud2020.consumer.feign.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wjc
 * @date 2021/1/16
 */
@SpringBootApplication
@EnableFeignClients
public class EurekaConsumerFeignOrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerFeignOrderMain80.class, args);
    }
}
