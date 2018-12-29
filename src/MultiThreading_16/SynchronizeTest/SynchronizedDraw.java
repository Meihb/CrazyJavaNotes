package MultiThreading_16.SynchronizeTest;

public class SynchronizedDraw {
    public static void main(String[] args) {
        SynchronizedAccount saccount = new SynchronizedAccount("123456", 1000);
        new SynchronizedDrawThread("甲", saccount, 800).start();
        new SynchronizedDrawThread("甲", saccount, 800).start();
    }
}
