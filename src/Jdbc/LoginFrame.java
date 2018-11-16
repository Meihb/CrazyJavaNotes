package Jdbc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.security.PrivilegedAction;
import java.sql.*;
import java.util.Properties;

public class LoginFrame {
    private final String PROP_FILE = System.getProperty("user.dir") + "/src/Jdbc/mysql.ini";
    //    private String driver;
    //url 数据库服务器地址
    private String url;
    private String user;
    private String pass;
    //登录界面GUI
    private JFrame jFrame = new JFrame("登录");
    private JTextField userField = new JTextField(20);
    private JTextField passField = new JTextField(20);
    private JButton loginButton = new JButton("登录");

    /**
     * 检查用户登录信息
     *
     * @param userName
     * @param userPass
     * @return
     */
    private boolean validateByStatement(String userName, String userPass) {

        try (
                Connection conn = DriverManager.getConnection(url, user, pass);
                Statement stmt = conn.createStatement();
        ) {

            ResultSet rs = stmt.executeQuery("SELECT 1 FROM `frame_users` WHERE  fuser_name = '" + userName + "' " +
                    "AND fuser_pass =  '" + userPass + "' LIMIT 1");
            if (!rs.next()) {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 检查用户登录信息
     *
     * @param userName
     * @param userPass
     * @return
     */
    private boolean validateByPreparedStatement(String userName, String userPass) {

        try (
                Connection conn = DriverManager.getConnection(url, user, pass);
                PreparedStatement pstmt = conn.prepareStatement(
                        "SELECT 1 FROM `frame_users` WHERE fuser_name = ? AND fuser_pass = ? LIMIT 1"
                );
        ) {
            pstmt.setString(1, userName);
            pstmt.setString(2, userPass);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 初始化用户表
     *
     * @throws Exception
     */
    public void initDatabase() throws Exception {
        Connection conn = DriverManager.getConnection(url, user, pass);
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("CREATE  TABLE  IF NOT EXISTS `frame_users`(" +
                "fuser_id int primary key auto_increment," +
                "fuser_name varchar(128) not null ," +
                "fuser_pass varchar(128) not null ," +
                "fuser_last_login datetime " +
                ")");
        //检查当前是否存在初始化用户
        ResultSet rs_user = stmt.executeQuery("" +
                "SELECT fuser_id,fuser_name,fuser_pass FROM `frame_users`  WHERE  fuser_name = 'root'");
        if (rs_user != null && rs_user.next()) {
            if (!rs_user.getString(3).equals("1234")) {
                //重置初始密码
                stmt.executeUpdate("UPDATE `frame_users` SET `fuser_pass` = '1234' WHERE fuser_id = " + rs_user.getInt(1));
            }
        } else {
            //创建初始化记录
            stmt.executeUpdate("INSERT INTO `frame_users` (fuser_name,fuser_pass) VALUE ('root','1234')");
        }
    }

    /**
     * 初始化gui
     *
     * @throws Exception
     */
    public void init() throws Exception {
        Properties prop = new Properties();
        prop.load(new FileInputStream(PROP_FILE));

        url = prop.getProperty("url");
        user = prop.getProperty("user");
        pass = prop.getProperty("pass");

        loginButton.addActionListener((ActionEvent action) -> {
            System.out.println(action);
            if (validateByPreparedStatement(userField.getText(), passField.getText())) {
                JOptionPane.showMessageDialog(jFrame, "登陆成功","SUCCESS",JOptionPane.INFORMATION_MESSAGE );
            } else {
                JOptionPane.showMessageDialog(jFrame, "登陆失败","ERROR",JOptionPane.ERROR_MESSAGE);
            }
        });
        jFrame.setLayout(new BorderLayout());
        jFrame.add(userField, BorderLayout.NORTH);
        jFrame.add(passField,BorderLayout.CENTER);
        jFrame.add(loginButton, BorderLayout.SOUTH);
        jFrame.pack();

        initDatabase();
        jFrame.setVisible(true);

    }

    public static void main(String[] args) throws Exception {
        new LoginFrame().init();
    }
}
