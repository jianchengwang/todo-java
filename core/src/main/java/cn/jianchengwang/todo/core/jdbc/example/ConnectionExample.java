package cn.jianchengwang.todo.core.jdbc.example;

import java.sql.*;
import java.util.Properties;

public class ConnectionExample {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // loading a JDBC driver
        Class.forName("org.mariadb.jdbc.Driver");

        // get connection
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test?user=root&password=123456");
        Connection connection2 = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test", "root", "123456");
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "123456");
        Connection connection3 = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test", properties);

        // close connection
        connection.close();
        try (Connection connection1 =  DriverManager.getConnection("jdbc:mariadb://localhost:3306/test?user=root&password=123456")) {

        }

        // commit
        connection.setAutoCommit(false);
        connection.commit();
        connection.setAutoCommit(true);

        // rollback
        try {
            connection.setAutoCommit(false);

            // do something

            connection.commit();
        } catch (Exception e) {

            connection.rollback();
        }

        // create statement
        Statement statement = connection.createStatement();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from user where id = ?");

        // get metadata
        DatabaseMetaData databaseMetaData = connection.getMetaData();

    }
}
