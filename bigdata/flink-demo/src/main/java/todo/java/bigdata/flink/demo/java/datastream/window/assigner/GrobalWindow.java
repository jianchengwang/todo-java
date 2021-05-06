package todo.java.bigdata.flink.demo.java.datastream.window.assigner;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.GlobalWindows;
import todo.java.bigdata.flink.demo.java.tool.GenTool;

/**
 * @author wjc
 * @date 2020/11/2
 */
public class GrobalWindow {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        // 构建输入数据
        DataStreamSource<Tuple2<String, Long>> input = env.fromCollection(GenTool.genCollection());

        // 创建 GlobalWindow
        input.keyBy(1)
                .window(GlobalWindows.create())
                .sum(1).print();

        env.execute();
    }
}
