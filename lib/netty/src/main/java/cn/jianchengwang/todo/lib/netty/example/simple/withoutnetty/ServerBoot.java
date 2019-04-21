package cn.jianchengwang.todo.lib.netty.example.simple.withoutnetty;

public class ServerBoot {

    private static final int port = 9000;

    public static void main(String[] args) {

        Server server = new Server(port);
        new Thread(server).start();

    }
}
