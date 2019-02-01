package NetworkProgramming.SeniorTest;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.UnexpectedException;

class ClientThread implements Runnable {

    //客户端负责的输入流
    BufferedReader br = null;

    public ClientThread(BufferedReader br) {
        this.br = br;
    }

    @Override
    public void run() {
        //读取服务端输入流
        try {
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}

public class Client {
    private static final int SERVER_PORT = 30000;
    private Socket socket;
    private PrintStream ps;
    private BufferedReader brServer;
    private BufferedReader brKey;


    public void init() {

        try {
            //初始化键盘输入
            brKey = new BufferedReader(new InputStreamReader(System.in));
            socket = new Socket();
            //连接到服务器端
            socket.connect(new InetSocketAddress("127.0.0.1", SERVER_PORT), 10000);
            //获取Socket输出和输出流
            ps = new PrintStream(socket.getOutputStream());
            brServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String tip = "";

            while (true) {
                String userName = JOptionPane.showInputDialog(tip + "输入用户名");
                //增加协议字符发送信息
                ps.println(CrazyitProtocol.USER_ROUND + userName + CrazyitProtocol.USER_ROUND);

                //读取服务器的响应
                String result = brServer.readLine();
                if (result.equals(CrazyitProtocol.NAME_REP)) {//姓名重复,重新输入
                    tip = "用户名重复,请重新";
                    continue;
                }
                if (result.equals(CrazyitProtocol.LOGIN_SUCCESS)) {
                    //登陆成功,结束循环
                    break;

                }

            }
        } catch (UnknownHostException uhe) {
            System.out.println("找不到远程服务器,请确定服务器已经启动");
            closeRs();
            System.exit(1);
        } catch (IOException ioe) {
            System.out.println("网络异常,请重新登陆");
            closeRs();
            System.exit(1);
        }
        //启动
        new Thread(new ClientThread(brServer)).start();


    }


    //读取键盘输入,发送服务端
    private void readAndSend() {
        String line = null;
        try {
            while ((line = brKey.readLine()) != null) {
                //如果发送的信息里面有冒号,且以//开头,则认为发送私聊信息
                if (line.indexOf(":") > 0 && line.startsWith("//")) {
                    line = line.substring(2);//去除//和:
                    ps.println(CrazyitProtocol.PRIVATE_ROUND +
                            line.split(":")[0] + CrazyitProtocol.SPLIYT_SIGN + line.split(":")[1] + CrazyitProtocol.PRIVATE_ROUND);
                } else {//公共聊天
                    ps.println(CrazyitProtocol.MSG_ROUND + line + CrazyitProtocol.MSG_ROUND);
                }
            }
        } catch (IOException ioe) {
            System.out.println("网络通信异常!请重新登陆!");
            closeRs();
            System.exit(1);
        }

    }

    private void closeRs() {
        try {
            if (brKey != null) {
                brKey.close();
            }
            if (brServer != null) {
                brServer.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.init();
        client.readAndSend();
    }

}
