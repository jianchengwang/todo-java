package todo.springcloud.atguigucloud2020.consumer.feign.hystrix.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wjc
 * @date 2021/1/16
 */
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class EurekaConsumerFeignHystrixOrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerFeignHystrixOrderMain80.class, args);
    }
}
