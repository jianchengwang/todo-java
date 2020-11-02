package todo.lib.flink.datastream.window.assigner;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import todo.lib.flink.common.source.Tuple2Source;
import todo.lib.flink.tool.GenTool;

import java.util.List;

/**
 * @author wjc
 * @date 2020/11/2
 */
@Slf4j
public class SlidingWindow {
    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 构建输入数据
        DataStreamSource<Tuple2<String, Long>> input = env.addSource(new Tuple2Source());

        // 使用 ProcessTime 滑动窗口, 2s 为一个窗口长度, 每 1s 滑动一次
        input.keyBy(x -> x.f1)
                .timeWindow(Time.seconds(2), Time.seconds(1))
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
