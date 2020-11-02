package todo.lib.flink.common.source;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.Random;
import java.util.UUID;

/**
 * @author wjc
 * @date 2020/11/2
 */
public class Tuple2Source implements SourceFunction<Tuple2<String,Long>> {
    private volatile boolean isRunning = true;
    private volatile Integer index = 0;
    private Random random = new Random();

    @Override
    public void run(SourceContext<Tuple2<String, Long>> ctx) throws Exception {
        while (isRunning){
            Thread.sleep(1000);
            String id = "index" + index ;
            Long value = Long.valueOf(index);
            ctx.collect(Tuple2.of(id, value));
            index++;
        }
    }

    @Override
    public void cancel() {
        isRunning = false;
    }
}
