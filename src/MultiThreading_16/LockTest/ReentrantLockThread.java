package MultiThreading_16.LockTest;

public class ReentrantLockThread extends Thread {

    private ReentrantAccount account;
    private double drawAmount;

    public ReentrantLockThread() {
    }

    public ReentrantLockThread(String name, ReentrantAccount account, double drawAmount) {
        super(name);
        this.account = account;
        this.drawAmount = drawAmount;
    }

    @Override
    public void run() {
        account.draw(drawAmount);
    }


    public static void main(String[] args) {
        ReentrantAccount ra = new ReentrantAccount("123456", 1000);
        new ReentrantLockThread("甲", ra, 600).start();
        new ReentrantLockThread("甲", ra, 800).start();
    }
}
