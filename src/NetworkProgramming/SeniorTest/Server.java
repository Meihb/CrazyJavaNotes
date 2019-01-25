package NetworkProgramming.SeniorTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;


class ServerThread implements Runnable {

    Socket s = null;
    BufferedReader br = null;
    PrintStream ps = null;

    ServerThread(Socket s) {
        this.s = s;

    }


    @Override
    public void run() {

        try {
            //获取Socket对应的输入流
            br = new BufferedReader(new InputStreamReader(s.getInputStream(), "utf-8"));
            //输出流
            PrintStream ps = new PrintStream(s.getOutputStream());
            String line = null;

            while ((line = br.readLine()) != null) {
                //如果读到CrazyitProtocol.USER_ROUND开始,并以其结束,则可以确定为用户登录名
                if (line.startsWith(CrazyitProtocol.USER_ROUND) && line.endsWith(CrazyitProtocol.USER_ROUND)) {
                    String userName = getRealMsg(line);
                    //检查用户名是否重复
                    if (Server.clents.map.containsKey(userName)) {
                        System.out.println("姓名重复");
                        ps.println(CrazyitProtocol.NAME_REP);
                    } else {
                        System.out.println("成功");
                        ps.println(CrazyitProtocol.LOGIN_SUCCESS);

                        //加入到clients
                        Server.clents.put(userName, ps);
                    }
                }
                //私聊信息
                else if (line.startsWith(CrazyitProtocol.PRIVATE_ROUND) && line.endsWith(CrazyitProtocol.PRIVATE_ROUND)) {
                    String privateInfo = getRealMsg(line);

                    String privateUser = privateInfo.split(CrazyitProtocol.SPLIYT_SIGN)[0];
                    String privateMsg = privateInfo.split(CrazyitProtocol.SPLIYT_SIGN)[1];

                    //输出流
                    Server.clents.map.get(privateUser).println(Server.clents.getKeyByValue(ps) + "悄悄对你说:" + privateMsg);
                } else if (line.startsWith(CrazyitProtocol.MSG_ROUND) && line.endsWith(CrazyitProtocol.MSG_ROUND)) {
                    //公屏聊天
                    String publicMsg = getRealMsg(line);
                    //输出流
                    for (PrintStream clientPs : Server.clents.valueSet()
                    ) {
                        //发送信息
                        clientPs.println(Server.clents.getKeyByValue(clientPs) + "说:" + publicMsg);
                    }

                }
            }
        } catch (IOException ioe) {
            Server.clents.removeByValue(ps);
            System.out.println(Server.clents.map.size());
            //关闭网络,IO资源
            try {
                if (br != null) {
                    br.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (s != null) {
                    s.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


    private String getRealMsg(String msg) {
        return msg.substring(CrazyitProtocol.PROTOCOL_LEN, msg.length() - CrazyitProtocol.PROTOCOL_LEN);
    }
}

public class Server {
    private static final int SERVER_PORT = 30000;
    //使用Map对象保存客户名字和对应输出流之间的对应关系
    public static CrazyitMap<String, PrintStream> clents = new CrazyitMap<>();

    public void init() {
        try (
                ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        ) {
            while (true) {
                Socket s = serverSocket.accept();
                System.out.println("Client 喜加一");

                new Thread(new ServerThread(s)).start();
            }
        } catch (Exception e) {
            System.out.println("服务器启动失败,报错信息:" + e.getMessage());
        }
    }


    public static void main(String[] args) {
        Server server = new Server();
        server.init();
    }
}
