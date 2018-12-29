package MultiThreading_16.ThreadCommunication;

class DrawThread extends Thread {
    private Account account;
    private double drawAmount;

    public DrawThread(String name, Account account, double drawAmount) {
        super(name);
        this.account = account;
        this.drawAmount = drawAmount;
    }

    @Override
    public void run() {
        for (int i=0;i<10;i++)
            account.draw(drawAmount);
    }
}

class DepositThread extends Thread {
    private Account account;
    private double depositAmount;

    public DepositThread(String name, Account account, double depositAmount) {
        super(name);
        this.account = account;
        this.depositAmount = depositAmount;
    }

    @Override
    public void run() {
        for (int i=0;i<10;i++)
            account.deposit(depositAmount);
    }
}

public class DrawTest {
    public static void main(String[] args) {
        Account account = new Account("123456", 0);

        new DrawThread("取款者甲", account, 800).start();
//        new DrawThread("取款者乙", account, 800).start();

        new DepositThread("存款者甲", account, 800).start();
        new DepositThread("存款者乙", account, 800).start();
        new DepositThread("存款者丙", account, 800).start();
    }
}
