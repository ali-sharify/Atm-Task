package service;

import model.Account;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Ali Sharifi
 * <p>ali.sharifi.dev@gmail.com<p>
 */
public class AccountService {
    private Map<String, Account> accounts = new HashMap<>();

    public void createAccount(String accountNumber, String pinHash, BigDecimal balance) {
        accounts.put(accountNumber, new Account(accountNumber, pinHash, balance));
    }

    public Optional<Account> getAccount(String accountNumber) {
        return Optional.ofNullable(accounts.get(accountNumber));
    }

    public boolean verifyAccount(Account account, String pin) {
        return account.getPinHash().equals(pin);
    }

    public void credit(Account account, BigDecimal amount) {
        if (amount.signum() <= 0) throw new RuntimeException("invalid amount");
        account.setBalance(account.getBalance().add(amount).setScale(2));
    }

    public void debit(Account account, BigDecimal amount) {
        if (amount.signum() <= 0) throw new IllegalArgumentException("Invalid amount");
        if (account.getBalance().compareTo(amount) < 0) throw new IllegalArgumentException("Insufficient balance");
        account.setBalance(account.getBalance().subtract(amount).setScale(2));
    }

    public BigDecimal getBalance(Account account) {
        return account.getBalance();
    }
}
