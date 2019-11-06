package org.songdan.drools.research.model;

/**
 * @author: Songdan
 * @create: 2019-11-06 17:19
 **/
public class Account {

    private Integer balance;


    public Account() {}
    public Integer getBalance() {
        return balance;
    }
    public void setBalance(Integer balance) {
        this.balance = balance;
    }
    public Account(Integer balance) {
        super();
        this.balance = balance;
    }
    public void withdraw(int money) {
        balance -= money;
    }

}
