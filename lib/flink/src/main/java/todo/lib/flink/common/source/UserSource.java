package todo.lib.flink.common.source;

import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;
import todo.lib.flink.common.model.User;


/**
 * @author wjc
 * @date 2020/11/1
 */
public class UserSource extends RichParallelSourceFunction<User> {
    private boolean running = true;

    @Override
    public void run(SourceContext<User> ctx) throws Exception {
        Integer[] ids = new Integer[10];
        String[] names = new String[10];
        for (int i = 0; i < 10; i++) {
            ids[i] = i + 1;
            names[i] = "gen_user_" + (i + 1);
        }
        while (running) {
            for (int i = 0; i < 10; i++) {
                ctx.collect(new User(ids[i], names[i]));
            }
            Thread.sleep(100);
        }
    }

    @Override
    public void cancel() {
        this.running = false;
    }
}
