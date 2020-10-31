package todo.core.jdbc.example;

import todo.core.jdbc.kit.JDBCKit;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

public class DatabaseMetaDataExample {


    public static void main(String[] args) {

        try (Connection connection = JDBCKit.getConn();) {

            DatabaseMetaData databaseMetaData = connection.getMetaData();

            // Database Product Name and Version
            int    majorVersion   = databaseMetaData.getDatabaseMajorVersion();   System.out.println(majorVersion);
            int    minorVersion   = databaseMetaData.getDatabaseMinorVersion();   System.out.println(minorVersion);
            String productName    = databaseMetaData.getDatabaseProductName();    System.out.println(productName);
            String productVersion = databaseMetaData.getDatabaseProductVersion(); System.out.println(productVersion);

            // Database Driver Version
            int driverMajorVersion = databaseMetaData.getDriverMajorVersion(); System.out.println(driverMajorVersion);
            int driverMinorVersion = databaseMetaData.getDriverMinorVersion(); System.out.println(driverMinorVersion);

            // Listing Tables
            String   catalog          = null;
            String   schemaPattern    = null;
            String   tableNamePattern = null;
            String[] types            = null;
            ResultSet result = databaseMetaData.getTables(
                    catalog, schemaPattern, tableNamePattern, types );

            while(result.next()) {
                String tableName = result.getString(3); System.out.println(tableName);
            }

            // Listing Columns in a Table
            String   catalog1           = null;
            String   schemaPattern1     = null;
            String   tableNamePattern1  = "user";
            String   columnNamePattern1 = null;


            ResultSet result1 = databaseMetaData.getColumns(
                    catalog1, schemaPattern1,  tableNamePattern1, columnNamePattern1);

            while(result1.next()){
                String columnName = result1.getString(4);
                int    columnType = result1.getInt(5);
                System.out.println(columnName + ":" + columnType);
            }

            // Primary Key for Table
            String   catalog2   = null;
            String   schema2    = null;
            String   tableName2 = "user";

            ResultSet result2 = databaseMetaData.getPrimaryKeys(
                    catalog2, schema2, tableName2);

            while(result.next()){
                String columnName = result2.getString(4); System.out.println(columnName + ":" + columnName);

            }

            // Supported Features
            databaseMetaData.supportsGetGeneratedKeys();

            databaseMetaData.supportsGroupBy();

            databaseMetaData.supportsOuterJoins();


        } catch (Exception e) {

            e.printStackTrace();
        }

    }
}
