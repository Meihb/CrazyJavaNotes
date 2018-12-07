package Ioin_15;

import java.io.File;
import java.io.FileReader;
import java.io.PushbackReader;

/*
退回输入流
 */
public class PushbackTest {
    public static void main(String[] args) {
        try ( //处理流
              PushbackReader pushbackReader = new PushbackReader(new FileReader("src/Ioin_15/PushbackTest.java"), 64);
        ) {
            char[] cbuf = new char[32];
            //保存上次读取的字符串内容
            String lastContent = "";
            int hasRead = 0;
            //循环读取
            while ((hasRead = pushbackReader.read(cbuf)) > 0) {
                //江都区的内容转为字符串
                String content = new String(cbuf, 0, hasRead);
                int targetIndex = 0;
                //将上次读取的字符串和本次读取的字符串拼接
                //查看是否包含目标字符串
                if ((targetIndex = (lastContent + content).indexOf("new PushbackReader")) > 0) {
                    //将拼接字符串推回输入流
                    pushbackReader.unread((lastContent + content).toCharArray());
                    //重新定义一个长度为targetIndex的char数组
                    if (targetIndex > 32) {
                        cbuf = new char[targetIndex];
                    }
                    //再次读取指定长度内容
                    pushbackReader.read(cbuf, 0, targetIndex);
                    //打印读取内容
                    System.out.println(new String(cbuf, 0, targetIndex));
                    System.exit(1);
                } else {
                    //打印上次读取的内容
                    System.out.println(lastContent);
                    lastContent  = content;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
