package MultiThreading_16.SynchronizeTest;

public class DrawTest {
    public static void main(String[] args) {
        Account account = new Account("123456", 1000);
        //模拟两个线程取钱
        new DrawThread("甲",account,800).start();
        new DrawThread("已",account,800).start();
    }
}
