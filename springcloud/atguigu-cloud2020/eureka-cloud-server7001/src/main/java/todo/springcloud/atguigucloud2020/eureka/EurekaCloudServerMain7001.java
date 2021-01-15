package todo.springcloud.atguigucloud2020.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author wjc
 * @date 2021/1/15
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaCloudServerMain7001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaCloudServerMain7001.class, args);
    }
}
