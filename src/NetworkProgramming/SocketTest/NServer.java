package NetworkProgramming.SocketTest;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;

public class NServer {
    //定义检测所有Channel状态的Selector
    private Selector selector = null;
    static final int PORT = 30000;
    //定义实现编码、解码的字符集对象
    private Charset charset = Charset.forName("UTF-8");

    public void init() throws IOException {
        selector = Selector.open();
        //打开一个ServerSocketChannel实例
        ServerSocketChannel server = ServerSocketChannel.open();
        InetSocketAddress isa = new InetSocketAddress("127.0.0.1", PORT);
        //绑定到具体Ip
        server.bind(isa);
        //设置非阻塞方式
        server.configureBlocking(false);
        //注册到selector
        server.register(selector, SelectionKey.OP_ACCEPT);//server只允许accept

        //获取到IO处理,否则阻塞等待
        while (selector.select() > 0) {
            //一次处理selector上的每个已经选择的SelectorKey(即需要IO)
            for (SelectionKey sk : selector.selectedKeys()) {
                //先从中删除待处理此sk
                selector.selectedKeys().remove(sk);
                //如果此sk是连接请求
                if (sk.isAcceptable()) {
                    //接受连接
                    SocketChannel sc = server.accept();
                    //设置费阻塞模式
                    sc.configureBlocking(false);
                    //将该SocketChannel注册到selector
                    sc.register(selector, SelectionKey.OP_READ);
                    //将sk对应的channel设置成准备接受其它请求
                    sk.interestOps(SelectionKey.OP_ACCEPT);
                }
                //如果对应的sk得channel有对应的的数据需要读取
                if (sk.isReadable()) {
                    //获取该SelectionKey对应的Channel，该Channel中有可读数据
                    SocketChannel sc = (SocketChannel) sk.channel();

                    //Buffer
                    ByteBuffer buff = ByteBuffer.allocate(16);
                    String content = "";
                    //开始读取数据
                    try {
                        while ((sc.read(buff)) > 0) {
                            System.out.println("before pos is :" + buff.position());
                            buff.flip();
                            System.out.println("after pos is :" + buff.position());
                            content += charset.decode(buff);
                            System.out.println("finally pos is :" + buff.position());
                        }
                        System.out.println("读取的数据:" + content);
                        //将sk对应的Channel设置成下一次读取
                        sk.interestOps(SelectionKey.OP_READ);

                    }
                    //如果捕获到sk对应的Channel出现异常,表名该Channel对应的client出现了问题,则取消此sk对应的注册
                    catch (IOException ioe) {
                        sk.cancel();
                        if (sk.channel() != null) {
                            sk.channel().close();
                        }
                    }

                    //如果content的长度大于0,级聊天信息不为空
                    if (content.length() > 0) {
                        //遍历SelectionKey
                        for (SelectionKey key : selector.keys()) {
                            //获取对应channel
                            Channel targetChannel = key.channel();
                            if (targetChannel instanceof SocketChannel) {
                                //写入
                                SocketChannel dest = (SocketChannel) targetChannel;
                                dest.write(charset.encode(content));
                            }
                        }
                    }
                }


            }
        }

    }

    public static void main(String[] args) throws IOException {
        new NServer().init();
    }

}
