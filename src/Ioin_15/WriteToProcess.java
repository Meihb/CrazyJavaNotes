package Ioin_15;


import java.io.IOException;
import java.io.PrintStream;

public class WriteToProcess {
    public static void main(String[] args) throws IOException {
        Process p = Runtime.getRuntime().exec(new String[]{"cmd", "/c", "java  ReadStandard"});

        try (
                PrintStream ps = new PrintStream(p.getOutputStream());
        ) {
            ps.println("普通字符串");
            ps.println(new WriteToProcess());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
