package todo.lib.netty.todo.web.server.bootstrap;

import todo.lib.netty.todo.web.server.Const;
import todo.lib.netty.todo.web.server.annotation.Action;
import todo.lib.netty.todo.web.server.kit.PagKit;
import todo.lib.netty.todo.web.server.route.Route;
import lombok.Data;

import java.util.*;

@Data
public class InitBootstrap {

    String pkgName;
    public InitBootstrap(String pkgName) {
        this.pkgName = pkgName;
    }

    public void init() {

        Set<String> clazzNameSet = PagKit.findClasses(pkgName);
        clazzNameSet.forEach(clazzName -> {

            Class clazz = null;
            try {
                clazz = Class.forName(clazzName);

                if(clazz.isAnnotationPresent(Action.class)) {

                    Action action = (Action) clazz.getAnnotation(Action.class);
                    String uri = action.value().length()==0?clazz.getSimpleName():action.value();
                    Const.ROUTE_MAP.put(uri, new Route(uri, clazz, null));
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        });

    }
}
