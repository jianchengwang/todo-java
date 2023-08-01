package todo.java.geekbang.designpattern.behavioral.template;

import java.sql.*;

/**
 * @author jianchengwang
 * @date 2023/6/15
 */

public class JDBCDemo {
    public User queryUser(long id) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // load and register JDBC driver for MySQL
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "xzg", "xzg");

            // create Statement object
            stmt = conn.createStatement();

            // execute SQL
            String sql = "select * from user where id=" + id;
            ResultSet resultSet = stmt.executeQuery(sql);

            String eid = null, ename = null, price = null;

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                return user;
            }
        } catch (ClassNotFoundException e) {
            // TODO: log...
        } catch (SQLException e) {
            // TODO: log...
        } finally {
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO: log...
                }
            if (stmt != null)
                try {
                    stmt.close();
                } catch (SQLException e) {
                    // TODO: log...
                }
        }
        return null;
    }

}
