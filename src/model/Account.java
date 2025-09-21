package model;

import java.math.BigDecimal;

/**
 * @author Ali Sharifi
 * <p>ali.sharifi.dev@gmail.com<p>
 */
public class Account {
    private final String accountNumber;
    private String pinHash;
    private BigDecimal balance;

    public Account(String accountNumber, String pinHash, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.pinHash = pinHash;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getPinHash() {
        return pinHash;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}
