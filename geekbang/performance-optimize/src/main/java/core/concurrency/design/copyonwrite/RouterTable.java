package core.concurrency.design.copyonwrite;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author jianchengwang
 * @date 2023/4/28
 */
//路由表信息
public class RouterTable {
    //Key:接口名
    //Value:路由集合
    ConcurrentHashMap<String, CopyOnWriteArraySet<Router>>
            rt = new ConcurrentHashMap<>();
    //根据接口名获取路由表
    public Set<Router> get(String iface){
        return rt.get(iface);
    }
    //删除路由
    public void remove(Router router) {
        Set<Router> set=rt.get(router.getIface());
        if (set != null) {
            set.remove(router);
        }
    }
    //增加路由
    public void add(Router router) {
        Set<Router> set = rt.computeIfAbsent(
                router.getIface(), r ->
                        new CopyOnWriteArraySet<>());
        set.add(router);
    }
}
