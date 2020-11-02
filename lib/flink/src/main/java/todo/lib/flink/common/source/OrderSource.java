package todo.lib.flink.common.source;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.UUID;

/**
 * @author wjc
 * @date 2020/11/2
 */
public class OrderSource implements SourceFunction<Tuple2<String,Long>> {
    private volatile boolean isRunning = true;

    @Override
    public void run(SourceContext<Tuple2<String,Long>> ctx) throws Exception{
        while (isRunning){
            Thread.sleep(1000);
            //订单id
            String orderid = UUID.randomUUID().toString();
            //订单完成时间
            long orderFinishTime = System.currentTimeMillis();
            ctx.collect(Tuple2.of(orderid, orderFinishTime));
        }
    }

    @Override
    public void cancel(){
        isRunning = false;
    }
}
