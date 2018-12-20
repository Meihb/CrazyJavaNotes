package Ioin_15.NIO;

import java.nio.CharBuffer;

public class BufferTest {
    public static void main(String[] args) {
        //创建Buffer,new io,特征是不再如Inputstream/OutputStream阻塞线程
        CharBuffer cbuff = CharBuffer.allocate(8);//分配容量
        System.out.println("capaticy :" + cbuff.capacity());
        System.out.println("limit :" + cbuff.limit());
        System.out.println("position :" + cbuff.position());

        //放入元素
        cbuff.put("aa");
        cbuff.put("b");
        cbuff.put("c");

        System.out.println("加入三次元素以后,当前position ：" + cbuff.position());
        //flip(),快速翻转,将当前position设置为limit,然后清零pos;即重新出路已经读写过得数据，一般可以用来读取以后输出
        cbuff.flip();
        System.out.println("limit :" + cbuff.limit());
        System.out.println("position :" + cbuff.position());
        //取出第一个元素
        System.out.println("第一个元素(position=0):" + cbuff.get());
        System.out.println("取出一个元素以后,position :" + cbuff.position());
        //调用clear方法 将position置为0，limit设为capatity ,可以再次读写数据
        cbuff.clear();
        System.out.println("clear()后limit:" + cbuff.limit() + ";position:" + cbuff.position());
        System.out.println(cbuff.get());//此时数据并未清空
        cbuff.get(3);
        //执行绝对读取,不影响当前position的位置
        System.out.println("执行绝对读取之后的position:"+cbuff.position());

    }
}
