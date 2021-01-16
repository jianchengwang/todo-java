package todo.springcloud.atguigucloud2020.eureka.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author wjc
 * @date 2021/1/16
 */
@SpringBootApplication
@EnableConfigServer
public class EurekaConfigServerMain3344 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaConfigServerMain3344.class, args);
    }
}
