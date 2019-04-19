package cn.jianchengwang.todo.core.jdbc.example;

import cn.jianchengwang.todo.core.jdbc.kit.JDBCKit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class QueryOrUpdateExample {

    public static void main(String[] args) {


        String querySql = "select * from user";
        String preparedSql = "update user set name = 'hello' where id = ?";

        try (Connection connection = JDBCKit.getConn();
             Statement statement = connection.createStatement(
                     ResultSet.TYPE_FORWARD_ONLY,
                     ResultSet.CONCUR_READ_ONLY,
                     ResultSet.CLOSE_CURSORS_AT_COMMIT);
             PreparedStatement preparedStatement = connection.prepareStatement(preparedSql);
             ResultSet resultSet = statement.executeQuery(querySql);) {

            /**
             * A ResultSet can have one of two concurrency levels:
             *
             * ResultSet.CONCUR_READ_ONLY
             * ResultSet.CONCUR_UPDATABLE
             * CONCUR_READ_ONLY means that the ResultSet can only be read.
             *
             * CONCUR_UPDATABLE means that the ResultSet can be both read and updated.
             */
            int nameIndex   = resultSet.findColumn("name");
            while (resultSet.next()) {

                String name = resultSet.getString(nameIndex);
                long   age  = resultSet.getLong("age");

                System.out.println(name);
                System.out.println(age);

//                resultSet.updateInt("age", 55); // throw exception because we use CONCUR_READ_ONLY
//                resultSet.updateRow();
            }


            String updateSql = "update user set name = 'zhangqiaoxia' where id = 1";
            int rowsAffected = statement.executeUpdate(updateSql);

            preparedStatement.setInt(1, 1);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
