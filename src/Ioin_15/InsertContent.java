package Ioin_15;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;

public class InsertContent {

    /**
     * @param fileName      文件名称
     * @param pos           文件插入位置
     * @param insertContent 文件内容
     */
    public static void insert(String fileName, long pos, String insertContent) {
        File back_file = new File("stream_bak.txt");
        System.out.println(back_file.exists());
        byte[] bbuf = new byte[64];
        int hasRead = 0;
        back_file.deleteOnExit();
        try (
                //创建raf对象
                RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
                FileOutputStream fos = new FileOutputStream(back_file);
                FileInputStream fis = new FileInputStream(back_file);
        ) {
            boolean outFlag = pos < raf.length();
            if (!back_file.exists()) {
                back_file.createNewFile();

            }
            System.out.println(back_file.exists());
            //初始化指针位置
            pos = outFlag ? pos : raf.length();
            raf.seek(pos);
            //中途插入字符 ,设置指针落点 且缓存后续内容
            if (outFlag) {
                //备份内容
                while ((hasRead = raf.read(bbuf)) > 0) {
                    fos.write(bbuf,0,hasRead);
                }
            }
            raf.seek(pos);
            //复写
            raf.write(insertContent.getBytes());
            if (outFlag) {
                //写入 bak文件的备份内容
                while ((hasRead = fis.read(bbuf)) > 0) {
                    raf.write(bbuf,0,hasRead);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        insert("insert.txt", 7, "你们好啊,大家好");
    }
}
