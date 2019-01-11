package NetworkProgramming;

import java.net.InetAddress;

public class InetAddressTest {

    public static void main(String[] args) throws Exception {
        InetAddress ip = InetAddress.getByName("www.crazyit.org");

        System.out.println(ip.isReachable(100));
        //获取IP字符串
        System.out.println(ip.getHostAddress());
        //根据原始IP获取对应的实例
        InetAddress local = InetAddress.getByAddress(new byte[]{127, 0, 0, 1});
        System.out.println(local.isReachable(100));
        System.out.println(local.getHostName());
    }
}
