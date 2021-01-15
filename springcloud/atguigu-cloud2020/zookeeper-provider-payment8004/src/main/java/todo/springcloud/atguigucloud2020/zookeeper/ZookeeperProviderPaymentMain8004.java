package todo.springcloud.atguigucloud2020.zookeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wjc
 * @date 2021/1/15
 */
@SpringBootApplication
@EnableDiscoveryClient //该注解用于向使用consul或者zookeeper作为注册中心时注册服务
public class ZookeeperProviderPaymentMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(ZookeeperProviderPaymentMain8004.class, args);
    }
}