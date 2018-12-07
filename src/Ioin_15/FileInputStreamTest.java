package Ioin_15;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class FileInputStreamTest {
    public static void main(String[] args) throws Exception {
        try (
                FileReader fr = new FileReader("src/Ioin_15/FileInputStreamTest.java");
        ) {
            char[] cbuf = new char[32];
            int canRead2 = 0;
            while ((canRead2 = fr.read(cbuf)) > 0) {
                System.out.println(new String(cbuf, 0, canRead2));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        System.out.println("====================================================");
        FileInputStream fis = new FileInputStream("src/Ioin_15/FileInputStreamTest.java");
        byte[] bbuf = new byte[1024];

        int canRead = 0;

        while ((canRead = fis.read(bbuf)) > 0) {
            System.out.println(new String(bbuf, 0, canRead));
        }
        fis.close();
    }
}
