package todo.springcloud.atguigucloud2020.nacos.sentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wjc
 * @date 2021/1/17
 */
@EnableDiscoveryClient
@SpringBootApplication
public class NacosSentinelMain8401 {
    public static void main(String[] args) {
        SpringApplication.run(NacosSentinelMain8401.class, args);
    }
}
