package Ioin_15;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileTest {
    public static void main(String[] args) throws IOException {
        //以当前路径创建File对象
        File file = new File(".");
//        File file = new File("Jdbc/mysql.ini");
        System.out.println(file.getAbsolutePath());

        //获取文件名 "."
        System.out.println(file.getName());
        //获取相对路径的父路径,可能出错从而返回null
        System.out.println(file.getParent());
        //获取绝对路径
        System.out.println(file.getAbsoluteFile());
        //获取绝对路径的上一级路径
        System.out.println(file.getAbsoluteFile().getParent());
        //在当前路径创建临时文件
        File tempFile = File.createTempFile("aaa", ".txt", file);
        try {
            Thread.sleep(2);
        } catch (Exception e) {

        }
        //指定JVM退出时删除改文件
        tempFile.deleteOnExit();
        //以系统时间为名创建新文件
        File newFile = new File(System.currentTimeMillis() + ".txt");
        System.out.println("newFile对象是否存在:" + newFile.exists());
        //以指定File对象创建一个文件
        if (!newFile.exists())
            System.out.println();
        newFile.createNewFile();
        newFile.deleteOnExit();
        //创建目录
        System.out.println(newFile.mkdir());
        //使用list()方法列出当前路径所有文件和路径
        String[] fileList = file.list();
        System.out.println("===当前路径下所有文件和路径如下===");
        for (String fileName : fileList) {
            System.out.println(fileName);
        }

    }
}

