package todo.java.bigdata.flink.demo.java.datastream.sink;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.redis.RedisSink;
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommand;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommandDescription;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisMapper;
import todo.java.bigdata.flink.demo.java.common.model.User;
import todo.java.bigdata.flink.demo.java.common.source.UserSource;

/**
 * @author wjc
 * @date 2020/11/1
 */
public class RedisSink_ {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        DataStreamSource<User> stream = env.addSource(new UserSource());

        FlinkJedisPoolConfig conf = new FlinkJedisPoolConfig.Builder().setHost("122.51.87.176").setPort(6379).setPassword("123456").build();

        stream.addSink(new RedisSink<>(conf, new MyRedisSink()));

        env.execute();
    }

    public static class MyRedisSink implements RedisMapper<User> {
        @Override
        public String getKeyFromData(User user) {
            return user.getId().toString();
        }

        @Override
        public String getValueFromData(User User) {
            return User.getName();
        }

        @Override
        public RedisCommandDescription getCommandDescription() {
            return new RedisCommandDescription(RedisCommand.HSET, "flink-test");
        }
    }
}
