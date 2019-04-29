package cn.jianchengwang.todo.lib.netty.todo.web.demo;

import cn.jianchengwang.todo.lib.netty.todo.web.server.action.IWorkAction;
import cn.jianchengwang.todo.lib.netty.todo.web.server.action.param.ParamMap;
import cn.jianchengwang.todo.lib.netty.todo.web.server.annotation.Action;

@Action("demo")
public class DemoAction implements IWorkAction {

    @Override
    public void execute(ParamMap param) {

        System.out.println("demo action execute .....");

        String name = param.getString("name");
        Integer age = param.getInteger("age");
        User user = new User(name, age);

        System.out.println("name: " + user.getName());
        System.out.println("age: " + user.getAge());

        System.out.println("demo action execute done .....");

    }
}
