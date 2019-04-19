package cn.jianchengwang.todo.core.jdbc.example;

import cn.jianchengwang.todo.core.jdbc.kit.JDBCKit;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class CallableStatementExample {

    public static void main(String[] args) {

        try(Connection connection = JDBCKit.getConn();
            CallableStatement callableStatement =
                    connection.prepareCall("{call calculateStatistics(?, ?)}");) {

            callableStatement.setString(1, "param1");
            callableStatement.setInt   (2, 123);

            // 返回结果集的存储过程
            ResultSet result1 = callableStatement.executeQuery();

            // 没有结果集的存储过程
            callableStatement.executeUpdate();

            // 批量操作存储过程
            callableStatement.setString(1, "param1");
            callableStatement.setInt   (2, 123);
            callableStatement.addBatch();
            callableStatement.setString(1, "param2");
            callableStatement.setInt   (2, 456);
            callableStatement.addBatch();
            int[] updateCounts = callableStatement.executeBatch();

            // 存储过程返回参数
            callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(2, java.sql.Types.INTEGER);

            // 建议您在尝试访问任何输出参数之前先处理结果集。出于数据库兼容性的原因，建议这样做。
            ResultSet result = callableStatement.executeQuery();
            while(result.next()) {
                // do something
            }

            String out1 = callableStatement.getString(1);
            int    out2 = callableStatement.getInt   (2);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
