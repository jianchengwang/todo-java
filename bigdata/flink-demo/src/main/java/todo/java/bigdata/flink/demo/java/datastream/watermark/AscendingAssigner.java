package todo.java.bigdata.flink.demo.java.datastream.watermark;

import org.apache.flink.api.common.eventtime.*;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import todo.java.bigdata.flink.demo.java.common.source.OrderSource;

import java.util.Date;

/**
 * @author wjc
 * @date 2020/11/2
 */
public class AscendingAssigner {
    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //设置周期性水印的间隔
        env.getConfig().setAutoWatermarkInterval(5000L);

        // 指定系统时间概念为 event time
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        // 使用 Ascending 分配 时间信息和 watermark
        DataStream<Tuple2<String, Long>> dataStream = env.addSource(new OrderSource());
        DataStream<Tuple2<String,Long>> withTimestampsAndWatermarks = dataStream.assignTimestampsAndWatermarks(
                (WatermarkStrategy<Tuple2<String, Long>>) context -> new WatermarkGenerator<Tuple2<String,Long>>(){
                    private long maxTimestamp;
                    private long delay = 3000;
                    @Override
                    public void onEvent(
                            Tuple2<String,Long> event,
                            long eventTimestamp,
                            WatermarkOutput output){
                        maxTimestamp = Math.max(maxTimestamp, event.f1);
                    }
                    @Override
                    public void onPeriodicEmit(WatermarkOutput output){
                        output.emitWatermark(new Watermark(maxTimestamp - delay));
                    }
                });

        withTimestampsAndWatermarks.process(new ProcessFunction<Tuple2<String,Long>,Object>(){

            @Override
            public void processElement(
                    Tuple2<String,Long> value, Context ctx, Collector<Object> out) throws Exception{
                long w = ctx.timerService().currentWatermark();
                System.out.println(" 水印 ： " + w + "  water  date " + new Date(w) + " now " +
                        new Date(value.f1));
            }
        });

        try {
            env.execute();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
