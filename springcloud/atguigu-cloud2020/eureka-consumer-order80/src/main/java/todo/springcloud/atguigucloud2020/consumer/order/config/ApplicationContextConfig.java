package todo.springcloud.atguigucloud2020.consumer.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author wjc
 * @date 2021/1/15
 */
@Configuration
public class ApplicationContextConfig {
    @Bean
//    @LoadBalanced // 赋予restTemplate负载均衡能力
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
