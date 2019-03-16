package cn.jianchengwang.todo.gradle.model;

import lombok.Data;
import lombok.NonNull;

@Data
@NonNull
public class TodoItem {

    private Long id;
    private String name;

    public TodoItem() {

    }

    public TodoItem(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
