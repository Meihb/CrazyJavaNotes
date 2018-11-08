package Exception_10;

import java.io.FileInputStream;
import java.io.IOException;

public class FinallyTest {
    public static void main(String[] args) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("a.txt");
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            //return 强制退回
        }
    }
}
