package Ioin_15.CharSetPack;

import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class FileLockTest {
    public static void main(String[] args) throws Exception {
        try (
                //使用FileOutputStream获取FileChannel
                FileChannel foc = new FileOutputStream("lock.txr").getChannel();
        ) {
            //使用非阻塞是方式对指定文件加锁
            FileLock fl = foc.tryLock();
            System.out.println(fl.isShared());
            Thread.sleep(10000);
            //释放
            fl.release();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
