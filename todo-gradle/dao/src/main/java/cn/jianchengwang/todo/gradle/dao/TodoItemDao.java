package cn.jianchengwang.todo.gradle.dao;

import cn.jianchengwang.todo.gradle.model.TodoItem;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TodoItemDao {

    private final static List<TodoItem> todoItemList = new ArrayList<>();

    static {
        Properties properties = new Properties();
        try (InputStream is = TodoItemDao.class.getResourceAsStream("todoItem.properties");) {

            if(is != null) {
                properties.load(is);

                todoItemList.add(new TodoItem(Long.parseLong(properties.getProperty("id")), properties.getProperty("name")));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public List<TodoItem> all() {
        return todoItemList;
    }

    public void add(TodoItem todoItem) {
        todoItemList.add(todoItem);
    }

    public void remove(TodoItem todoItem) {
        todoItemList.remove(todoItem);
    }

}
