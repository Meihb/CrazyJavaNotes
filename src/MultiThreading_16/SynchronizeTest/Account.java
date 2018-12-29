package MultiThreading_16.SynchronizeTest;

import java.security.PrivilegedAction;

public class Account {
    //封装账户编号、账户余额、的两个成员变量
    private String accountNO;
    private double balance;

    public Account() {
    }

    public Account(String accountNO, double balance) {
        this.accountNO = accountNO;
        this.balance = balance;
    }

    public void setAccountNO(String accountNO) {
        this.accountNO = accountNO;
    }

    public String getAccountNO() {
        return accountNO;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    /**
     * 重写 hashCode方法
     * @return
     */
    @Override
    public int hashCode() {
        return accountNO.hashCode();
    }

    /**
     * 重写equals方法
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj.getClass() == Account.class)) {
            Account target = (Account) obj;
            return target.getAccountNO().equals(accountNO);

        } else {
            return false;
        }
    }
}
