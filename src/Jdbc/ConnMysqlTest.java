package Jdbc;

import java.sql.*;

public class ConnMysqlTest {
    public static void main(String[] args) throws Exception {
//        Class.forName("com.mysql.jdbc.Driver");
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://118.25.41.135:3306/test?useSSL=true&serverTimezone=GMT%2B8",
                    "dwts", "dwts");
            Statement stmt = conn.createStatement();
            /*
            execute()执行后返回boolean值,如果执行后第一个结果是ResultSet则返回true,否在返回false
            executeQuery()执行select语句,返回查询到的结果集
            executeUpdate()执行DML语句,返回受影响的行数
             */
            ResultSet rs = stmt.executeQuery("SELECT * FROM `baidu_index` WHERE 1");
            while (rs.next()) {
                System.out.println(rs.getInt(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("sqlState is " + sqle.getSQLState());
        }

    }
}
