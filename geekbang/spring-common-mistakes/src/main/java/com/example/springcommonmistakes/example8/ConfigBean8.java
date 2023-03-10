package com.example.springcommonmistakes.example8;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jianchengwang
 * @date 2023/3/10
 */
@Configuration
public class ConfigBean8 {
    @Bean
    public LightService8 getTransmission(){
        return new LightService8();
    }
}
