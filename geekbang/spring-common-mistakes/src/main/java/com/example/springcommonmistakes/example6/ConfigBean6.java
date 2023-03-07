package com.example.springcommonmistakes.example6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * @author jianchengwang
 * @date 2023/3/7
 */
@Configuration
public class ConfigBean6 {

    @Bean
    public Student6 student1(){
        return createStudent(1, "xie");
    }

    @Bean
    public Student6 student2(){
        return createStudent(2, "fang");
    }


    /**
     * 没生效
     * DefaultListableBeanFactory#resolveMultipleBeans
     * @return
     */
    @Bean
    public List<Student6> students(){
        Student6 student3 = createStudent(3, "liu");
        Student6 student4 = createStudent(4, "fu");
        return Arrays.asList(student3, student4);
    }

    private Student6 createStudent(int id, String name) {
        Student6 student = new Student6();
        student.setId(id);
        student.setName(name);
        return student;
    }
}
