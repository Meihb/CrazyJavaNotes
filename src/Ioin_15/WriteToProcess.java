package Ioin_15;


import java.io.IOException;

public class WriteToProcess {
    public static void main(String[] args) throws IOException {
        Process p = Runtime.getRuntime().exec("javac");
    }
}
