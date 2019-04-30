package cn.jianchengwang.todo.lib.netty.todo.web.server.action;

import cn.jianchengwang.todo.lib.netty.todo.web.server.context.param.ParamMap;
import cn.jianchengwang.todo.lib.netty.todo.web.server.context.WebContext;

public interface IWorkAction {
    void execute(WebContext context) throws Exception;
}
