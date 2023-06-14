package todo.java.geekbang.designpattern.creational.factory.di;

/**
 * @author jianchengwang
 * @date 2023/6/14
 */

public class RateLimiter {
    private RedisCounter redisCounter;
    public RateLimiter(RedisCounter redisCounter) {
        this.redisCounter = redisCounter;
    }
    public void test() {
        System.out.println("Hello World!");
    }
    //...
}
