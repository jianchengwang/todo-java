package todo.lib.netty.todo.web.demo;

import todo.lib.netty.todo.web.server.bootstrap.InitBootstrap;
import todo.lib.netty.todo.web.server.netty.HttpServer;

public class App {

    public static void main(String[] args) throws Exception {

        new HttpServer(8989)
                .boot(new InitBootstrap("cn.jianchengwang.todo.lib.netty.todo.web.demo"))
                .start();
    }
}
