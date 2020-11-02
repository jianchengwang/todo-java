package todo.lib.flink.datastream.window.function;

import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import todo.lib.flink.common.source.Tuple2Source;

/**
 * @author wjc
 * @date 2020/11/2
 */
public class ReduceFunction_ {
    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 构建输入数据
        DataStreamSource<Tuple2<String, Long>> input = env.addSource(new Tuple2Source());

        input.keyBy(x -> x.f1)
                .timeWindow(Time.seconds(1), Time.seconds(1))
                .reduce((ReduceFunction<Tuple2<String, Long>>) (t1, t2) -> new Tuple2<>(t1.f0 + t2.f0, t1.f1))
                .print();
        env.execute();
    }
}
