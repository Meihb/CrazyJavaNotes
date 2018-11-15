package Jdbc;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class PreparedStatementTest {
    //    private String driver;
    private String url;
    private String user;
    private String pass;

    public void initParam(String paramFile) throws Exception {
        //使用Properties类加载属性文件
        Properties properties = new Properties();
        properties.load(new FileInputStream(paramFile));
//        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        user = properties.getProperty("user");
        pass = properties.getProperty("pass");

        System.out.println(url );
        //加载驱动
//        Class.forName(driver);
    }

    public void insertUseStatement() throws Exception {
        long start = System.currentTimeMillis();

        try (
        //获取数据库连接
                Connection conn = DriverManager.getConnection(url, user, pass);
        Statement stmt = conn.createStatement();
        ){
            for (int i = 0; i < 100; i++) {
                stmt.executeUpdate("INSERT INTO `jdbc_test` (jdbc_name,jdbc_desc) values " +
                        "('姓名" + i + "',null )");
            }
            System.out.println("使用statement耗时:" + (System.currentTimeMillis() - start));

        }
    }

    public void insertUsePrepare() throws Exception {
        long start = System.currentTimeMillis();
        try (
                //获取数据库连接
                Connection conn = DriverManager.getConnection(url, user, pass);
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO `jdbc_test` (jdbc_name,jdbc_desc) values (?,?) ");
        ) {
            for (int i = 100; i < 200; i++) {
                pstmt.setString(1, "姓名" + i);
                pstmt.setString(2, null);
                pstmt.executeUpdate();
            }
            System.out.println("使用prepareStatement耗时:" + (System.currentTimeMillis() - start));

        }
    }

    public static void main(String[] args) throws Exception {
        PreparedStatementTest pt = new PreparedStatementTest();
        System.out.println(System.getProperty("user.dir"));
        pt.initParam(System.getProperty("user.dir") + "/src/Jdbc/mysql.ini");
        pt.insertUseStatement();
        pt.insertUsePrepare();
    }
}
