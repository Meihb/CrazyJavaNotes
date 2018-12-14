package Ioin_15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadFromProcess {
    public static void main(String[] args) throws IOException {
        try {
            String[] cmd = {"cmd", "D:\\version_control\\git\\CrazyJavaNotes\\", "notepad.exe"};
            Process p = Runtime.getRuntime().exec("notepad.exe");
            System.out.println(p);
            /*
            Process进程下IO流
            InputStream getErrorStream() 错误流
            InputStream getInputStream() 输入流
            OutStream   getOutoutStream() 子进程输出流
             */
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
