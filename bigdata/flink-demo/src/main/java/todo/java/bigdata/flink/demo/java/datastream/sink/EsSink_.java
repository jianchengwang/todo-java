package todo.java.bigdata.flink.demo.java.datastream.sink;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.elasticsearch.ElasticsearchSinkFunction;
import org.apache.flink.streaming.connectors.elasticsearch6.ElasticsearchSink;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Requests;
import todo.java.bigdata.flink.demo.java.common.model.User;
import todo.java.bigdata.flink.demo.java.common.source.UserSource;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author wjc
 * @date 2020/11/1
 */
public class EsSink_ {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        DataStreamSource<User> stream = env.addSource(new UserSource());

        ArrayList<HttpHost> httpHosts = new ArrayList<>();
        httpHosts.add(new HttpHost("localhost", 9200, "http"));
        ElasticsearchSink.Builder<User> sensorReadingBuilder = new ElasticsearchSink.Builder<>(
                httpHosts,
                (ElasticsearchSinkFunction<User>) (user, runtimeContext, requestIndexer) -> {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("data", user.toString());
                    IndexRequest indexRequest = Requests
                            .indexRequest()
                            .index("flink-test") // 索引是flink-test，相当于数据库
                            .type("user") // es6需要加这一句
                            .source(map);

                    requestIndexer.add(indexRequest);
                }
        );
        sensorReadingBuilder.setBulkFlushMaxActions(1);
        stream.addSink(sensorReadingBuilder.build());

        env.execute();
    }
}
