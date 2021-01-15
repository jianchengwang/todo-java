package todo.springcloud.atguigucloud2020.provider.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author wjc
 * @date 2021/1/15
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class EurekaProviderPaymentMain8002 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaProviderPaymentMain8002.class, args);
    }
}
