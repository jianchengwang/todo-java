package todo.java.geekbang.designpattern.creational.factory.di;

/**
 * @author jianchengwang
 * @date 2023/6/14
 */

public class RedisCounter {
    private String ipAddress;
    private int port;
    public RedisCounter(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }
    //...
}
