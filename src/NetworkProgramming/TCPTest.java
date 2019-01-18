package NetworkProgramming;

import java.net.InetAddress;
import java.net.ServerSocket;

class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(3000, 1000,InetAddress.getByName("127.0.0.1"));
    }
}

public class TCPTest {
}
