package todo.java.bigdata.flink.demo.java.datastream.datasource;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import todo.java.bigdata.flink.demo.java.common.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 自定义数据源,模仿着写一个从 MySQL 中读取数据的 Source
 * @author wjc
 * @date 2020/10/31
 */
public class CustomizeDataSourceJDBC {

    public static void main(String[] args) throws Exception {

        class SourceFromMySQL extends RichSourceFunction<User> {
            PreparedStatement ps;
            private Connection connection;

            @Override
            public void run(SourceContext<User> ctx) throws Exception {
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    User user = new User(
                            resultSet.getInt("id"),
                            resultSet.getString("name").trim());
                    ctx.collect(user);
                }
            }

            @Override
            public void cancel() {

            }

            /**
             * open() 方法中建立连接，这样不用每次 invoke 的时候都要建立连接和释放连接。
             *
             * @param parameters
             * @throws Exception
             */
            @Override
            public void open(Configuration parameters) throws Exception {
                super.open(parameters);
                connection = getConnection();
                String sql = "select * from t_user;";
                ps = this.connection.prepareStatement(sql);
            }

            /**
             * 程序执行完毕就可以进行，关闭连接和释放资源的动作了
             *
             * @throws Exception
             */
            @Override
            public void close() throws Exception {
                super.close();
                if (connection != null) { //关闭连接和释放资源
                    connection.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }

            private Connection getConnection() {
                Connection con = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8", "root", "123456");
                } catch (Exception e) {
                    System.out.println("-----------mysql get connection has exception , msg = "+ e.getMessage());
                }
                return con;
            }
        }

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        SourceFromMySQL sourceFromMySQL = new SourceFromMySQL();
        env.addSource(sourceFromMySQL).print();
        env.execute("Flink customizeDataSource test");
    }
}
