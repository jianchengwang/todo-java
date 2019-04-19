package cn.jianchengwang.todo.core.jdbc.model;

import lombok.Data;

@Data
public class User {

    private Long id;
    private String name;
    private Integer age;

    public User() {}
    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
