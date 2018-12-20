package Ioin_15.NIO;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class ReadFile {

    public static void main(String[] args) throws IOException {

        try (
                //创建文件输入流
                FileInputStream fis = new FileInputStream(
                        System.getProperty("user.dir") + "/src/Ioin_15/NIO/ReadFile.java");
                FileChannel fc = fis.getChannel();
        ) {
            ByteBuffer bbuff = ByteBuffer.allocate(256);
            Charset charset = Charset.forName("UTF-8");
            CharsetDecoder decoder = charset.newDecoder();


            System.out.println("初始pos："+bbuff.position());
            while (fc.read(bbuff) != -1) {
                //此时

                bbuff.flip();//position复零
                System.out.println("复原之后:"+bbuff.position());
                CharBuffer cbuffer = decoder.decode(bbuff);
                System.out.println(cbuffer);

                System.out.println("读取之后 position:" + bbuff.position());
                bbuff.clear();

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
