package cn.jianchengwang.todo.gradle.dao.test;

import cn.jianchengwang.todo.gradle.dao.TodoItemDao;
import cn.jianchengwang.todo.gradle.model.TodoItem;
import org.junit.Test;

public class TodoItemTest {

    private TodoItemDao todoItemDao = new TodoItemDao();

    @Test
    public void all() {

        System.out.println("all...");

        assert todoItemDao.all().size() == 0;
    }

    @Test
    public void add() {
        TodoItem todoItem = new TodoItem();
        todoItem.setId(0L);
        todoItem.setName("学习gradle");

        todoItemDao.add(todoItem);

        System.out.println("add...");

        assert todoItemDao.all().size() > 0;
    }

    @Test
    public void remove() {

        TodoItem todoItem = new TodoItem();
        todoItem.setId(0L);

        todoItemDao.remove(todoItem);

        System.out.println("remove...");

        assert todoItemDao.all().size() == 0;
    }
}

