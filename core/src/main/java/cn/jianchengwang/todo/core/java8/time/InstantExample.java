package cn.jianchengwang.todo.core.java8.time;

import java.time.Instant;

public class InstantExample {

    public static void main(String[] args) {

        // 创建一个Instant实例
        Instant now = Instant.now();

        // 访问Instant的时间
        long seconds = now.getEpochSecond();
        int  nanos   = now.getNano();
        System.out.println("seconds : " + seconds);
        System.out.println("nanos   : " + nanos);

        // 3秒后
        Instant later   = now.plusSeconds(3);
        // 3秒前
        Instant earlier = now.minusSeconds(3);

        System.out.println("current : " + now.toString());
        System.out.println("later   : " + later);
        System.out.println("earlier : " + earlier);
    }
}
