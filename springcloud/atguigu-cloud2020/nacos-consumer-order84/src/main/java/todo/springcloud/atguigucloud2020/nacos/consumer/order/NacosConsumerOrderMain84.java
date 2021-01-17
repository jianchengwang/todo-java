package todo.springcloud.atguigucloud2020.nacos.consumer.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wjc
 * @date 2021/1/17
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosConsumerOrderMain84 {
    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerOrderMain84.class,args);
    }
}
