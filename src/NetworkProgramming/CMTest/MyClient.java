package NetworkProgramming.CMTest;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;

//读取Server发送过来的消息并打印
class ClientThread implements Runnable {
    public Socket s = null;
    public BufferedReader br = null;

    public ClientThread(Socket s) throws IOException {
        this.s = s;
        this.br = new BufferedReader(new InputStreamReader(s.getInputStream()));
    }

    @Override
    public void run() {
        System.out.println("开始接受服务器消息");

        try {
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class MyClient {

    public static void main(String[] args) {

        try {

            //创建socket连接
            Socket s = new Socket();
            s.connect(new InetSocketAddress("127.0.0.1", 30000), 10000);
            //开启线程读取Server
            new Thread(new ClientThread(s)).start();
            //读取键盘输入,发送至服务器

            /*
            Socket传输数据好像必须要用Stream,尝试使用Writer失败
             */
            PrintStream ps = new PrintStream(s.getOutputStream());
//            PrintWriter pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream(), Charset.forName("utf-8")));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            while ((line = br.readLine()) != null) {
                if (line.length() > 0) {
                    System.out.println("获取到键盘输入!");
                    ps.println(line);
//                    pw.println("writer:"+line);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
