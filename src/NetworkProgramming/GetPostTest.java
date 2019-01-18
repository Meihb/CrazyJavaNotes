package NetworkProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class GetPostTest {
    /**
     * 向指定URL发送Get方式的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数 格式满足name1=value1&name2=value2
     * @return URL 远程资源的响应
     */
    public static String sendGet(String url, String param) {
        String result = "";
        String urlName = url + "?" + param;
        try {
            URL realUrl = new URL(urlName);
            //创建Connection对象
            URLConnection conn = realUrl.openConnection();
            //设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");

            //建立实际的连接
            conn.connect();
            //获取响应头字段
            Map<String, List<String>> map = conn.getHeaderFields();
            //遍历输出
//            for (String key:map.keySet()
//                 ) {
//                System.out.println(key+"-->"+map.get(key));
//
//            }
            map.keySet().forEach((key) -> {
                System.out.println(key+"-->"+map.get(key));
            });

            try (
                    //定义BufferedReader输入流来读取URL的响应
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            ) {
                String line;
                while ((line = in.readLine()) != null) {
                    result += "\n" + line;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 向指定URL发送Post方式的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数,格式满足name1=value1&name2=value2
     * @return URL 代表远程资源的响应
     */
    public static String sendPost(String url, String param) {
        String result = "";
        try {
            URL realUrl = new URL(url);
            //打开和URL的连接
            URLConnection conn = realUrl.openConnection();
            //设置通用属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");
            //POST设置,必不可少
            conn.setDoOutput(true);//设置为Output,即对主进程而言输出到Connection
            conn.setDoInput(true);


            try (
                    //获取URLConnection对象对应的输出流
                    PrintWriter out = new PrintWriter(conn.getOutputStream())
            ) {
                out.print(param);
                //flush输出流缓冲
                out.flush();
            }

            try (
                    //定义BufferedReader输入流读取URL的响应
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))
            ) {
                String line;
                while ((line = in.readLine()) != null) {
                    result += "\n" + line;
                }
            }

        } catch (Exception e) {
            System.out.println("发送POST请求出现异常!" + e);
            e.printStackTrace();
        }

        return result;
    }


    public static void main(String[] args) {
        //发送GET请求
        String s = GetPostTest.sendGet("http://www.acfun.cn/member/unRead.aspx", "uid=798191");
        System.out.println(s);
        //发送POST请求
//        String s1 = GetPostTest.sendPost("")

    }
}
