package cn.jianchengwang.todo.lib.netty.todo.web.demo;

import cn.jianchengwang.todo.lib.netty.todo.web.server.action.IWorkAction;
import cn.jianchengwang.todo.lib.netty.todo.web.server.context.param.ParamMap;
import cn.jianchengwang.todo.lib.netty.todo.web.server.annotation.Action;
import cn.jianchengwang.todo.lib.netty.todo.web.server.context.WebContext;

@Action("demo")
public class DemoAction implements IWorkAction {

    @Override
    public void execute(WebContext context) {

        System.out.println("demo action execute .....");

        ParamMap param = context.getRq().getParamMap();

        String name = param.getString("name");
        Integer age = param.getInteger("age");
        User user = new User(name, age);

        System.out.println("name: " + user.getName());
        System.out.println("age: " + user.getAge());

        System.out.println("demo action execute done .....");

    }
}
