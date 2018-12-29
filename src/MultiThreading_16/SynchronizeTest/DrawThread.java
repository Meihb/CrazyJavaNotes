package MultiThreading_16.SynchronizeTest;

public class DrawThread extends Thread {
    //模拟用户账号
    private Account account;
    //当前线程所希望获取的钱数
    private double drawAmount;

    public DrawThread(String name, Account account, double drawAmount) {
        super(name);
        this.account = account;
        this.drawAmount = drawAmount;
    }

    @Override
    public void run() {

        //同步代码块
        synchronized (account){


        //账户余额大于取钱数目
        if (account.getBalance() > drawAmount) {
            //吐出钞票
            System.out.println(getName() + " " + "取钱成功");
            try {
                Thread.sleep(1);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            //修改余额
            account.setBalance(account.getBalance() - drawAmount);
            System.out.println("\t 余额为:" + account.getBalance());

        } else {
            System.out.println("余额不足!");
        }

        }
    }
}
