package MultiThreading_16.ThreadCommunication;

public class Account {
    private String accountNO;
    private double balance;

    //表示账户中当前存取款标识
    /*
    false 已取款,待存款
    true  已存款,待取款
     */
    private boolean flag = false;

    //构造器
    public Account(String accountNO, double balance) {
        this.accountNO = accountNO;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    //取款
    public synchronized void draw(double drawAmount) {
        try {

            if (!flag) {//当前不可取款,阻塞当前线程并
                wait();
            } else { //当前可以取款


                System.out.print(Thread.currentThread().getName() + "进行取款:" + drawAmount);

                balance = balance - drawAmount;
                System.out.println("账户余额为:" + balance);
                flag = false;
                notifyAll();//唤醒当前等待的线程
            }
        } catch (Exception ie) {
            ie.printStackTrace();
        }
    }

    //存款
    public synchronized void deposit(double depositAmount) {
        try {

            if (flag) {
                wait();
            } else {//当前可以存款

                System.out.print(Thread.currentThread().getName() + "进行存款:" + depositAmount);
                balance = balance + depositAmount;
                System.out.println("账户余额为:" + balance);
                flag = true;
                notifyAll();//唤醒当前等待的线程

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
