package todo.java.bigdata.flink.demo.java.datastream.datasource;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

/**
 * 第三方数据源，这里以kafka作为简单例子
 * @author wjc
 * @date 2020/10/30
 */
public class KafkaDataSource {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("group.id", "groupA");
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("auto.offset.reset", "latest");
        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<>("flink-test", new SimpleStringSchema(), properties);
        consumer.setStartFromGroupOffsets();
        DataStream<String> dataStream = env
                .addSource(consumer);
        dataStream.print();

        env.execute("Flink kafka datasource test");
    }
}
