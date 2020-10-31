package todo.core.java8.time;

import java.time.ZoneId;
import java.util.TimeZone;

public class ZoneIdExample {

    public static void main(String[] args) {

        // 获取系统默认时区
        ZoneId defaultZoneId  = ZoneId.systemDefault();
        ZoneId shanghaiZoneId = ZoneId.of("Asia/Shanghai");

        // TimeZone 转换为 ZoneId
        ZoneId oldToNewZoneId = TimeZone.getDefault().toZoneId();

        System.out.println(defaultZoneId);
        System.out.println(shanghaiZoneId);
        System.out.println(oldToNewZoneId);

        System.out.println(ZoneId.getAvailableZoneIds());
    }
}
