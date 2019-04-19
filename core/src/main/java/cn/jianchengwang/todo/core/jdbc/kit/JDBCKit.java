package cn.jianchengwang.todo.core.jdbc.kit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class JDBCKit {

    private static String jdbcUrl = "jdbc:mariadb://localhost:3306/test?user=root&password=123456";

    static {

        try {
            Class.forName("org.mariadb.jdbc.Driver");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static Connection getConn() {

        try {
            return DriverManager.getConnection(jdbcUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
