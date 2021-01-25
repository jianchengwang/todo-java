package info.jianchengwang.todo.springboot2.si.cache.redis;

import info.jianchengwang.todo.springboot2.si.cache.redis.config.RedisCustomerConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author wjc
 * @date 2021/1/22
 */
@MapperScan("info.jianchengwang.todo.springboot2.si.cache.redis.mapper")
@Import({RedisCustomerConfig.class})
@SpringBootApplication
public class SiCacheRedisMain {
    public static void main(String[] args) {
        SpringApplication.run(SiCacheRedisMain.class, args);
    }
}
