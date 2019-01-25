package NetworkProgramming.CMTest;

import NetworkProgramming.SocketTest.Server;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.cert.TrustAnchor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;


/*
    服务器单对单处理客户端输入流并打印
 */
class ServerThread implements Runnable {
    //定义当前线程所处理的Socket
    public Socket s = null;
    //该线程所处理的输入流
    public BufferedReader br = null;

    public ServerThread(Socket s) throws IOException {
        System.out.println(" Client喜加一");
        this.s = s;
        this.br = new BufferedReader(new InputStreamReader(s.getInputStream()));
    }

    @Override
    public void run() {
        try {
            //提示客户端连接成功
            PrintStream ps_initiate = new PrintStream(s.getOutputStream());
            ps_initiate.println("连接成功");
            String line = null;
            while ((line = readFromSocket()) != null) {
                System.out.println("获取输入流" + line);
                //循环输出到SocketList列表中的各个Socket
                for (Socket newS : MyServer.socketList
                ) {
                    /*
                    同样,无法使用writer,无法理解
                     */
                    PrintStream ps = new PrintStream(newS.getOutputStream());
//                    PrintWriter ps = new PrintWriter(new OutputStreamWriter(newS.getOutputStream(), "utf-8"));
                    ps.println(line);
                    /*
                    切记不要关闭ps.close()似乎会把节点流同样关闭
                     */
                }
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    /**
     * 读取输入流,发生异常则从List中删除socket
     *
     * @return
     */
    public String readFromSocket() {

        try {
            String line = null;
            line = br.readLine();
            System.out.println(Thread.currentThread().getName() + "发来消息:" + line);
            return line;
        } catch (IOException e) {
            //关闭socket连接
            MyServer.socketList.remove(this.s);
            System.out.println(Thread.currentThread().getName() + "已经断开");
//            e.printStackTrace();
        }
        return null;
    }
}

public class MyServer {
    //定义保存所有Socket的ArrayList,并将其包装成线程安全
    public static List<Socket> socketList = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(30000, 1000, InetAddress.getByName("127.0.0.1"));
        System.out.println("initated  ServerSocket successfully");
        while (true) {
            Socket s = serverSocket.accept();
            socketList.add(s);

            //每个客户端连接成功后,创建一个新的线程为该客户端服务
            new Thread(new ServerThread(s)).start();
        }
    }
}
