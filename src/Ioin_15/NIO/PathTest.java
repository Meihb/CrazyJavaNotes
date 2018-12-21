package Ioin_15.NIO;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathTest {
    public static void main(String[] args) {

        //以当前路径创建Path对象
        Path path = Paths.get("filter.txt");
        System.out.println("path里面包含的路径数量:"+path.getNameCount());
        System.out.println("path的根路径:"+path.getRoot());
        //获取path对应的绝对路径
        Path absolutePath = path.toAbsolutePath();
        System.out.println(absolutePath);
        //获取绝对路径的路径数量？感觉就是路径深度
        System.out.println("绝对路径的数量:"+absolutePath.getNameCount());
        System.out.println(absolutePath.getRoot());
        System.out.println(absolutePath.getName(0));
        System.out.println(absolutePath.getName(1));
        System.out.println(absolutePath.getName(2));
        System.out.println(absolutePath.getName(3));
        //以多个String来创建Path对象
        Path path2 = Paths.get("D:","version_control","git");
        System.out.println(path2);
    }
}
