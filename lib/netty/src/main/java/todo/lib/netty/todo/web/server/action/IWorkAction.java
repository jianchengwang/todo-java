package todo.lib.netty.todo.web.server.action;

import todo.lib.netty.todo.web.server.context.WebContext;

public interface IWorkAction {
    void execute(WebContext context) throws Exception;
}
