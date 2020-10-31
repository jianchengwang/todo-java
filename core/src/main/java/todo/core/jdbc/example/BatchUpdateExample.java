package todo.core.jdbc.example;

import todo.core.jdbc.kit.JDBCKit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchUpdateExample {

    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;

        String sql = "update user set age = ? where id = ?";
        try {
            connection = JDBCKit.getConn();
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(sql);

            // 批量更新一般要放在事物里面，防止一些更新成功，一些更新失败，产生数据不一致
            connection.setAutoCommit(false);

            // Statement
            statement.addBatch("insert into user(name,age) values('zhangqiaoxia', 26)");
            statement.addBatch("insert into user(name,age) values('wjc', 27)");
            int[] recordsAffected1 = statement.executeBatch();

            // PreparedStatement
            preparedStatement.setInt(1, 100);
            preparedStatement.setInt(2, 1);
            preparedStatement.addBatch();

            preparedStatement.setInt(1, 200);
            preparedStatement.setInt(2, 2);
            preparedStatement.addBatch();

            int[] recordsAffected2 = preparedStatement.executeBatch();

            connection.commit();

        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();

        } finally {

            try {
                if(statement != null) statement.close();
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
