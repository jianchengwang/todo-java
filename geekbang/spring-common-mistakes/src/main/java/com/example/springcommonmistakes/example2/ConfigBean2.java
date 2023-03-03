package com.example.springcommonmistakes.example2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jianchengwang
 * @date 2023/3/3
 */
@Configuration
public class ConfigBean2 {
    @Bean
    public String serviceName(){
        return "MyServiceName";
    }
}
