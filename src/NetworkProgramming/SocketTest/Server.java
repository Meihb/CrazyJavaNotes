package NetworkProgramming.SocketTest;


import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(3000, 1000, InetAddress.getByName("127.0.0.1"));

        while (true) {
            Socket s = ss.accept();
            //把Socket的输出流包装成PrintStream
            PrintStream ps = new PrintStream(s.getOutputStream());
            //IO操作
            ps.println("您好,您收到了服务器的新年祝福");
            ps.close();
            s.close();
        }
    }
}