package todo.lib.netty.todo.web.demo;

import todo.lib.netty.todo.web.server.action.IWorkAction;
import todo.lib.netty.todo.web.server.annotation.Action;
import todo.lib.netty.todo.web.server.context.WebContext;
import todo.lib.netty.todo.web.server.context.wrapper.Rq;

@Action("demo")
public class DemoAction implements IWorkAction {

    @Override
    public void execute(WebContext context) throws Exception {

        System.out.println("demo action execute .....");

        Rq rq = context.getRq();

        String name = rq.getString("name");
        Integer age = rq.getInteger("age");
        User user = new User(name, age);

        System.out.println("name: " + user.getName());
        System.out.println("age: " + user.getAge());

        WebContext.me().getRp().json(user);

        System.out.println("demo action execute done .....");

    }
}
