package todo.springcloud.atguigucloud2020.eureka.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author wjc
 * @date 2021/1/16
 */
@SpringBootApplication
@EnableEurekaClient
public class EurekaGateWayCloudServerMain9527 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaGateWayCloudServerMain9527.class, args);
    }
}
