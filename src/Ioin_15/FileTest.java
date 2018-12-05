package Ioin_15;

import java.io.File;
import java.io.IOException;

public class FileTest {
    public static void main(String[] args) throws IOException {
        //以当前路径创建File对象
        File file = new File(".");
        System.out.println(file.getAbsolutePath() );
        System.out.println(file.getAbsoluteFile());

    }
}

