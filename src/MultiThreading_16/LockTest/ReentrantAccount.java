package MultiThreading_16.LockTest;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantAccount {
    //定义锁对象
    private final ReentrantLock lock = new ReentrantLock();
    private String accountNO;
    private double balance;

    public ReentrantAccount() {
    }

    public ReentrantAccount(String accountNO, double balance) {
        this.accountNO = accountNO;
        this.balance = balance;
    }

    public String getAccountNO() {
        return accountNO;
    }

    public double getBalance() {
        return balance;
    }

    public void setAccountNO(String accountNO) {
        this.accountNO = accountNO;
    }

    public void draw(double drwaAmount) {
        //加锁
        lock.lock();
        try {
            if (balance >= drwaAmount) {
                System.out.println(Thread.currentThread().getName() + "取钱成功,吐出钞票" + drwaAmount);
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                balance = balance - drwaAmount;
                System.out.println("剩余余额为:"+balance);
            } else {
                System.out.println("取钱失败,余额不足");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

}
