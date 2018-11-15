package Jdbc;

import java.sql.*;

public class ConnMysqlTest {
    public static void main(String[] args) throws Exception {
//        Class.forName("com.mysql.jdbc.Driver");
        try {
            //设置时区、字符编码(保存中文必须)
            Connection conn = DriverManager.getConnection("jdbc:mysql://118.25.41.135:3306/test?" +
                            "characterEncoding=UTF-8&useSSL=true&serverTimezone=GMT%2B8",
                    "dwts", "dwts");
            Statement stmt = conn.createStatement();
            /*
            execute()执行后返回boolean值,如果执行后第一个结果是ResultSet则返回true,否在返回false
            executeQuery()执行select语句,返回查询到的结果集
            executeUpdate()执行DML和DDL语句,前者返回受影响的行数,后者返回0
             */
            Integer rs_ddl = stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `jdbc_test` (" +
                    "jdbc_id int auto_increment primary key ," +
                    "jdbc_name varchar(256)," +
                    "jdbc_desc text )");
            System.out.println("ddl 返回" + rs_ddl);
            Integer rs_dml = stmt.executeUpdate("INSERT INTO `jdbc_test` (jdbc_name,jdbc_desc)value " +
                    "('王大锤','王大锤之妻之夫')," +
                    "('李德生','二十八画生')", Statement.RETURN_GENERATED_KEYS);//返回插入值
            System.out.println("dml 返回" + rs_dml);//affected_rows
            //获取自增长id 之一
            ResultSet rs_gk = stmt.getGeneratedKeys();
            if (rs_gk != null && rs_gk.next()) {//rs结果集初始位置为0行,无数据,真正的结果集从1行开始,故先next,需要便利的时候变脸条件也是如此
                int autoIncrementKey = rs_gk.getInt(1);
                System.out.println(autoIncrementKey);
            }
            //获取自增id之二
            ResultSet rs_key2 = stmt.executeQuery("SELECT LAST_INSERT_ID() ");
            if (rs_key2.next()) {
                System.out.println("获取主键"+rs_key2.getInt(1));
            }

            System.out.println();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM `baidu_index` WHERE 1");
//            while (rs.next()) {
//                System.out.println(rs.getInt(1));
//            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("sqlState is " + sqle.getSQLState());
        }

    }
}
