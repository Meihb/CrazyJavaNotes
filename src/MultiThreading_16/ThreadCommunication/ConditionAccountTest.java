package MultiThreading_16.ThreadCommunication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class ConditionAccount {
    //定义Lock
    private ReentrantLock lock = new ReentrantLock();
    //定义Condition
    private final Condition cond = lock.newCondition();
    private String accountNO;
    private double balance = 0;
    private boolean needDeposited = true;
    private double forDrawn = 0;


    public ConditionAccount(String accountNO, double balance) {
        this.accountNO = accountNO;
        this.balance = balance;
    }

    public void draw(double drawAmount) {
        lock.lock();
        try {
            System.out.println("尝试取款" + drawAmount + ",当前余额为" + balance);
            while (!(balance >= drawAmount)) {//余额不足
                System.out.println("余额不足,需要存款操作,等待中---");
                forDrawn += drawAmount;
                cond.await();
                System.out.println("存款足够");
            }
            System.out.println(Thread.currentThread().getName() + " 开始取钱:" + drawAmount);
            balance = balance - drawAmount;
            System.out.println("当前余额:" + balance);
            cond.signalAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void deposit(double depositAmount) {
        lock.lock();
        try {
            System.out.println("尝试存款" + depositAmount + ",当前余额为" + balance);
            while (!(forDrawn > balance)) {//当前无存款需求
                System.out.println("当前无存款需求,等待取款需求中---");
                cond.await();
                System.out.print("存款不足");
            }
            System.out.println(Thread.currentThread().getName() + " 开始存钱:" + depositAmount);
            balance = balance + depositAmount;
            forDrawn -= depositAmount;
            System.out.println("当前余额:" + balance);
            cond.signalAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}


class ConditionDrawThread extends Thread {
    private ConditionAccount account;
    private double amount;

    public ConditionDrawThread(String name, ConditionAccount conditionAccount, double drawAmount) {
        super(name);
        this.account = conditionAccount;
        this.amount = drawAmount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            account.draw(Math.random() * amount);
        }
    }
}

class ConditionDepositThread extends Thread {
    private ConditionAccount account;
    private double amount;

    public ConditionDepositThread(String name, ConditionAccount conditionAccount, double drawAmount) {
        super(name);
        this.account = conditionAccount;
        this.amount = drawAmount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            account.deposit(Math.random() * amount);
        }

    }
}

public class ConditionAccountTest {
    public static void main(String[] args) {
        ConditionAccount conditionAccount = new ConditionAccount("123456", 0);

        new ConditionDepositThread("存款者甲", conditionAccount, 1000).start();
//        new ConditionDepositThread("存款者乙",conditionAccount,Math.random()*1000).start();

        new ConditionDrawThread("取款者甲", conditionAccount, 700).start();
//        new ConditionDrawThread("取款者乙",conditionAccount,Math.random()*700).start();
//        new ConditionDrawThread("取款者丙",conditionAccount,Math.random()*700).start();
    }
}