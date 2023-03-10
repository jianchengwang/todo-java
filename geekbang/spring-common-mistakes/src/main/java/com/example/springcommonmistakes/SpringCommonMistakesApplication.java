package com.example.springcommonmistakes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
//@ComponentScans(value = { @ComponentScan(value = "com.example.exclude.controller") })
public class SpringCommonMistakesApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringCommonMistakesApplication.class, args);
        context.close();
    }

}
