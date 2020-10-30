package todo.lib.flink.quickstart;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * @author wjc
 * @date 2020/10/29
 */
public class WordCount {

    public static void main(String[] args) throws Exception {
        // 获取本地执行环境
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // 设置并行数量
        env.setParallelism(1);
        // 获取数据流
        DataStream<String> stream = env.socketTextStream("localhost", 9999);
        // 转换算子处理数据流并输出结果
        stream.flatMap(new Tokenizer()).keyBy(r -> r.f0).sum(1).print();
        // 执行
        env.execute("Flink Streaming Java API Skeleton");
    }

    public static class Tokenizer implements FlatMapFunction<String, Tuple2<String, Integer>> {
        @Override
        public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
            String[] stringList = value.split("\\s");
            for (String s : stringList) {
                // 使用out.collect方法向下游发送数据
                out.collect(new Tuple2(s, 1));
            }
        }
    }
}