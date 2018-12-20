package Ioin_15.NIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class FileChannelTest {
    public static void main(String[] args) {
        String user_dir = System.getProperty("user.dir");
        File f = new File(user_dir + "/src/Ioin_15/NIO/FileChannelTest.java");
        try (
                //创建FileInputStream,以改文件输入流创建FileChannel
                FileInputStream fis = new FileInputStream(f);
                FileChannel ic = fis.getChannel();//输入channel
                FileChannel oc = new FileOutputStream("channel.txt").getChannel();//输出channel
        ) {
            //将FileChannel里的全部数据映射到ByteBuffer
            MappedByteBuffer buffer = ic.map(FileChannel.MapMode.READ_ONLY, 0, f.length());

            //将buffer数据全部输出
            oc.write(buffer);
            //调用clear方法
            buffer.clear();
            System.out.println(buffer.position() + "," + buffer.capacity() + "," + buffer.limit());

            //使用GBK字符创建字符对象
            Charset charset = Charset.forName("UTF-8");
            //创建解码器
            CharsetDecoder charsetDecoder = charset.newDecoder();
            //使用解码器将ByteBuffer转换成CharBuffer
            CharBuffer cbuff = charsetDecoder.decode(buffer);
            System.out.println(cbuff);

        } catch (Exception e) {
            e.printStackTrace();
        }

        try (//使用raf
             RandomAccessFile raf = new RandomAccessFile("channel.txt", "rw");
             FileChannel fileChannel = raf.getChannel();
        ) {
            ByteBuffer byteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, raf.length());

            //此处Channel的position注意来
            System.out.println(fileChannel.position());
            fileChannel.position(raf.length());
            fileChannel.write(byteBuffer);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
