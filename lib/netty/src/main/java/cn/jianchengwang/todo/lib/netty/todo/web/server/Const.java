package cn.jianchengwang.todo.lib.netty.todo.web.server;

import cn.jianchengwang.todo.lib.netty.todo.web.server.route.Route;

import java.util.LinkedHashMap;
import java.util.Map;

public final class Const {

    public static final Map<String, Route> ROUTE_MAP = new LinkedHashMap<>();

    /**
     * 默认字符集
     */
    public static final String DEFAULT_CHAR_SET = "UTF-8";

    /**
     * 当前版本号
     */
    public static final String MARIO_VERSION = "1.0.0";


}
