package todo.lib.flink.tool;

import org.apache.flink.api.java.tuple.Tuple2;
import todo.lib.flink.common.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author wjc
 * @date 2020/11/1
 */
public class GenTool {
    public static List<Tuple2<String, Long>> genCollection() {
        List<Tuple2<String, Long>> data = new ArrayList<>();
        Tuple2<String, Long> a = new Tuple2<>("first event", 1L);
        Tuple2<String, Long> b = new Tuple2<>("second event", 2L);
        data.add(a);
        data.add(b);
        return data;
    }
}
