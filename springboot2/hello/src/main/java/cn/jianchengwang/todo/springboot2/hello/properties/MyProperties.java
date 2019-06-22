package cn.jianchengwang.todo.springboot2.hello.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class MyProperties {

    @Value("${name}")
    private String name;
    @Value("${age}")
    private Integer age;
}
