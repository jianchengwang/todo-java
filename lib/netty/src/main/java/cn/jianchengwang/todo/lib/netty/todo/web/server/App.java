package cn.jianchengwang.todo.lib.netty.todo.web.server;

import cn.jianchengwang.todo.lib.netty.todo.web.server.netty.HttpServer;

public class App {

    public static void main(String[] args) throws Exception {

        new HttpServer(8080).start();

    }
}
