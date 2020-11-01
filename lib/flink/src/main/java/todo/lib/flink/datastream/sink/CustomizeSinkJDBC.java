package todo.lib.flink.datastream.sink;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import todo.lib.flink.common.model.User;
import todo.lib.flink.common.source.UserSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @author wjc
 * @date 2020/11/1
 */
public class CustomizeSinkJDBC {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        DataStream<User> stream = env.addSource(new UserSource());

        stream.addSink(new MyJDBCSink());

        env.execute();
    }

    public static class MyJDBCSink extends RichSinkFunction<User> {
        private Connection conn;
        private PreparedStatement insertStmt;
        private PreparedStatement updateStmt;


        @Override
        public void invoke(User value, Context context) throws Exception {
            updateStmt.setString(1, value.getName());
            updateStmt.setInt(2, value.getId());
            updateStmt.execute();
            if (updateStmt.getUpdateCount() == 0) {
                insertStmt.setInt(1, value.getId());
                insertStmt.setString(2, value.getName());
                insertStmt.execute();
            }
        }

        @Override
        public void open(Configuration parameters) throws Exception {
            super.open(parameters);
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test",
                    "root",
                    "123456"
            );
            insertStmt = conn.prepareStatement("INSERT INTO t_user (id, name) VALUES (?, ?)");
            updateStmt = conn.prepareStatement("UPDATE t_user SET name = ? WHERE id = ?");
        }

        @Override
        public void close() throws Exception {
            super.close();
            insertStmt.close();
            updateStmt.close();
            conn.close();
        }
    }
}
