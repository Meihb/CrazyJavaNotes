package MultiThreading_16.SynchronizeTest;

public class SynchronizedDrawThread extends Thread {
    //模拟用户账号
    private SynchronizedAccount account;
    //当前线程所希望获取的钱数
    private double drawAmount;

    public SynchronizedDrawThread(String name, SynchronizedAccount account, double drawAmount) {
        super(name);
        this.account = account;
        this.drawAmount = drawAmount;
    }

    @Override
    public void run() {

        account.draw(drawAmount);
    }
}
