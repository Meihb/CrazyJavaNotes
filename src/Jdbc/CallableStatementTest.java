package Jdbc;

import java.sql.Connection;
import java.sql.*;

public class CallableStatementTest {
    public void createProcedure() {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://118.25.41.135:3306/test?" +
                                "characterEncoding=UTF-8&useSSL=true&serverTimezone=GMT%2B8",
                        "dwts", "dwts");
                Statement stmt = conn.createStatement();
        ) {
            String sql =
                    "DROP PROCEDURE  IF EXISTS add_pro;" +
                            "DELIMITER //;" +
                            "CREATE PROCEDURE   add_pro (IN a int ,IN b int ,OUT sum int )" +
                            "BEGIN" +
                            "SET sum = a+b ;" +
                            "END;" +
                            " ";
            System.out.println(sql);
            stmt.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
//        new CallableStatementTest().createProcedure();
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://118.25.41.135:3306/test?" +
                                "characterEncoding=UTF-8&useSSL=true&serverTimezone=GMT%2B8",
                        "dwts", "dwts");
                CallableStatement cstmt = conn.prepareCall("call add_pro(?,?,?)");
        ) {
            cstmt.setInt(1, 2);
            cstmt.setInt(2, 3);
            cstmt.registerOutParameter(3, Types.INTEGER);

            cstmt.execute();
            System.out.println(cstmt.getInt(3));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
