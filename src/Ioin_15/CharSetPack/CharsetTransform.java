package Ioin_15.CharSetPack;

import java.beans.Encoder;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class CharsetTransform {
    public static void main(String[] args) throws Exception {
        //创建简体中文对应的Charset
        Charset charset = Charset.forName("GBK");
        CharsetEncoder encoder = charset.newEncoder();
        CharsetDecoder decoder = charset.newDecoder();

        CharBuffer cbuff = CharBuffer.allocate(8);
        cbuff.put("a");
        cbuff.put("b");
        cbuff.put("c");
        cbuff.flip();//为何需要这一步?,encode也是从此buffer的position中开始计算吗 True
//        cbuff.position(1);

        //编码
        ByteBuffer bbuff = encoder.encode(cbuff);
        System.out.println(bbuff.position() + "," + bbuff.limit() + "," + bbuff.capacity());
        while (bbuff.position() < bbuff.limit()
        ) {
            System.out.println(bbuff.position());
            System.out.println(bbuff.get() + "");
        }
        bbuff.flip();
        System.out.println("\r\n"+decoder.decode(bbuff));
        bbuff.flip();
        //可以直接使用字符集的方法解码/编码
        System.out.println("\r\n"+charset.decode(bbuff));
    }
}
