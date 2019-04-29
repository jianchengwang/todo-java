package cn.jianchengwang.todo.lib.netty.todo.web.server;

import cn.jianchengwang.todo.lib.netty.todo.web.server.bootstrap.InitBootstrap;
import cn.jianchengwang.todo.lib.netty.todo.web.server.netty.HttpServer;
import io.netty.handler.codec.http.HttpMethod;

public class App {

    public static void main(String[] args) throws Exception {

        new HttpServer(8989)
                .boot(new InitBootstrap("cn.jianchengwang.todo.lib.netty.todo.web.demo"))
                .start();
    }
}
