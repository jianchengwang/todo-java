package cn.jianchengwang.todo.lib.netty.todo.web.server.route;

import cn.jianchengwang.todo.lib.netty.todo.web.server.action.IWorkAction;
import lombok.Data;

@Data
public class Route {

    private String uri;
    private Class<? extends IWorkAction> workAction;
    private String method;

    public Route() {
    }

    public Route(String uri, Class workAction, String method) {
        this.uri = uri;
        this.workAction = workAction;
        this.method = method;
    }
}
