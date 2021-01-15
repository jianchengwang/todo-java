package todo.springcloud.atguigucloud2020.consul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wjc
 * @date 2021/1/15
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsulProviderPaymentMain8006 {
    public static void main(String[] args) {
        SpringApplication.run(ConsulProviderPaymentMain8006.class, args);
    }
}
