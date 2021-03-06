package NetworkProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

class Server {
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

class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 3000);
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String line = br.readLine();
        System.out.println("服务器消息:"+line);
        br.close();
        socket.close();
    }

}

public class TCPTest {
    public static void main(String[] args) {

    }
}
