package Ioin_15;

import java.io.RandomAccessFile;

public class AppendContent {
    public static void main(String[] args) {
        try (
                //已读写方式打开一个RandomAccessFile对象
                RandomAccessFile raf = new RandomAccessFile("streamOut.txt", "rw");
        ) {
          //将指针设置到文件末尾
            raf.seek(raf.length());
            raf.write("追加内容\\r\\n:末尾".getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
