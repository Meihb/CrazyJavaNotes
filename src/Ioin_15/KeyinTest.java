package Ioin_15;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class KeyinTest {
    public static void main(String[] args) {

        try (InputStreamReader isr = new InputStreamReader(System.in);
             //缓冲流
             BufferedReader br = new BufferedReader(isr);
        ) {
            String line = null;
            while ((line = br.readLine()) != null) {
                if (line.toLowerCase().equals("exit")) {
                    System.out.println("退出");
                    System.exit(1);
                }
                //打印读取内容
                System.out.println("输出内容:"+line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
