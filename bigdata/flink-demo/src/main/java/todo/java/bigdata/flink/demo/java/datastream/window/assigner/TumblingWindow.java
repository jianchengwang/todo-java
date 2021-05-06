package todo.java.bigdata.flink.demo.java.datastream.window.assigner;

import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import todo.java.bigdata.flink.demo.java.common.source.Tuple2Source;

/**
 * @author wjc
 * @date 2020/11/2
 */
public class TumblingWindow {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 构建输入数据
        DataStreamSource<Tuple2<String, Long>> input = env.addSource(new Tuple2Source());

        // 使用 ProcessTime 滚动窗口, 2s 为一个窗口长度
        input.keyBy(x -> x.f1)
                .window(TumblingProcessingTimeWindows.of(Time.seconds(2)))
                .reduce(new MyWindowFunction())
                .print();

        env.execute();
    }

    public static class MyWindowFunction implements ReduceFunction<Tuple2<String, Long>> {
        @Override
        public Tuple2<String, Long> reduce(Tuple2<String, Long> t1, Tuple2<String, Long> t2) throws Exception {
            return new Tuple2<>(t1.f0, t1.f1 + t2.f1);
        }
    }
}
