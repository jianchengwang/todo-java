package todo.springcloud.atguigucloud2020.consumer.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import todo.springcloud.atguigucloud2020.consumer.myrule.MyRibbonRule;

/**
 * @author wjc
 * @date 2021/1/15
 */
@SpringBootApplication
@EnableEurekaClient
//@RibbonClient(name = "EUREKA-PROVIDER-PAYMENT", configuration = MyRibbonRule.class) // 定义ribbon规则
public class EurekaConsumerOrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerOrderMain80.class, args);
    }
}
