package Ioin_15.NIO;

import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FilesTest {
    public static void main(String[] args) throws Exception {
        String fsrc = "src/Ioin_15/NIO/FilesTest.java";
        Path path = Paths.get(fsrc);
        Charset charset = Charset.forName("UTF-8");
        //复制文件
        Files.copy(Paths.get(fsrc), new FileOutputStream("Files.txt"));
        //判断是否为隐藏文件
        System.out.println("FilesTest.java是否为隐藏文件:" + Files.isHidden(Paths.get(fsrc)));
        //一次性读取FilesTest.java的所有行
        List<String> lines = Files.readAllLines(path, charset);
        System.out.println(lines);
        System.out.println(Files.size(path));

        List<String> poem = new ArrayList<>();
        poem.add("子系中山狼");
        poem.add("得志便猖狂");
        poem.add("金闺花柳质");
        poem.add("一载赴黄粱");

        Files.write(Paths.get("poem.txt"), poem, Charset.forName("UTF-8"));

        Files.list(Paths.get(".")).forEach(

        info -> {
            System.out.println(

                 info

            );
        }
        );
    }
}
