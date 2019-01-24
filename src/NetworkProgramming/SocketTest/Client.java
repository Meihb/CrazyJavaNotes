package NetworkProgramming.SocketTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 3000);
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String line = br.readLine();
        System.out.println("服务器消息:" + line);
        br.close();
        socket.close();
    }

}