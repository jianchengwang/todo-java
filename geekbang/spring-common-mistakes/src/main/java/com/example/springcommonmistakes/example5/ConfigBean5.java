package com.example.springcommonmistakes.example5;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jianchengwang
 * @date 2023/3/3
 */
@Configuration
public class ConfigBean5 {
    @Bean
    public Student5 student5() {
        Student5 student = new Student5();
        student.setId(1);
        student.setName("hello");
        return student;
    }
}
