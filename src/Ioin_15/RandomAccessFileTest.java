package Ioin_15;

import java.io.RandomAccessFile;
import java.util.Random;

public class RandomAccessFileTest {
    public static void main(String[] args) {
        String user_dir = System.getProperty("user.dir");
        try (
                RandomAccessFile raf = new RandomAccessFile(user_dir+"/src/Ioin_15/RandomAccessFileTest.java", "r");
        ) {

            //获取初始位置
            System.out.println("RandomAccessFile的文件指针的初试位置:" + raf.getFilePointer());
            //移动记录指针
            raf.seek(1024);
            byte[] bbuf = new byte[1024];
            //用于保存实际读取的字节数
            int hasRead = 0;
            //使用循环来重复"取水"过程
            while ((hasRead = raf.read(bbuf)) > 0) {
                //去除字节,将字节数组转为字符串
                System.out.println(new String(bbuf, 0, hasRead));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
