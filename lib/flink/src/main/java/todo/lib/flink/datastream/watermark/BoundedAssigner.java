package todo.lib.flink.datastream.watermark;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import todo.lib.flink.tool.GenTool;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wjc
 * @date 2020/11/2
 */
public class BoundedAssigner {
    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 指定系统时间概念为 event time
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        List<Tuple2<String, Long>> collectionInput = GenTool.genCollection();

        DataStream<Tuple2<String, Long>> text = env.fromCollection(collectionInput);
        // 设置一个延迟3秒的固定延迟水印
        text.assignTimestampsAndWatermarks(WatermarkStrategy.forBoundedOutOfOrderness(Duration.ofSeconds(3)));

        env.execute();
    }
}
