package Ioin_15;

import java.io.File;

public class FilenameFilterTest {
    public static void main(String[] args) {
        char a = '中';
        File file = new File("src/Jdbc");
        System.out.println(file.exists());
        //使用ldmbda表达式实现文件过滤器,给list方法传入FilenameFilter参数
        String[] nameList = file.list(
                (dir, name) -> {
                    return name.endsWith(".java") || new File(name).isDirectory();
                }
        );
        try {
            for (String s : nameList) {
                System.out.println(s);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }
}
