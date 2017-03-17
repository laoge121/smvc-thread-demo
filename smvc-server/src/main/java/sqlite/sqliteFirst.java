package sqlite;

import java.sql.*;

/**
 * Created by yuhou on 2017/3/17.
 */
public class sqliteFirst {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.sqlite.JDBC");

        Connection connection = DriverManager.getConnection("jdbc:sqlite:test.db");

        Statement statement = connection.createStatement();

        /*
        String sql = "create table test(" +
                "id int primary key not null," +
                "name char(50) not null," +
                "password char(20) not null);";
        connection.setAutoCommit(false);
        int num = statement.executeUpdate(sql);
        if (num < 0) {
            throw new RuntimeException("创建异常!");
        }
        connection.commit();
        connection.setAutoCommit(true);*/


        String sql = "insert into test(id,name,password) values (2,'aa','11111')";
        int num = statement.executeUpdate(sql);
        if (num < 0) {
            throw new RuntimeException("插入异常!");
        }

        sql = "SELECT * FROM test;";

        Statement ps = connection.createStatement();

        ResultSet resultSet = ps.executeQuery(sql);
        while (resultSet.next())
            System.out.println(resultSet.getString("name"));


        resultSet.close();
        ps.close();
        connection.close();

    }

}
