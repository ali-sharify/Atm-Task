package service;

import model.Account;
import model.CardSession;
import model.Transaction;
import model.WithdrawResult;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Ali Sharifi
 * <p>ali.sharifi.dev@gmail.com<p>
 */
public class BankService {

    private final AccountService accountService;
    private final Map<String, CashDispenserService> cashDispenserService;

    private Map<String, Transaction> transactions = new HashMap<>();

    public BankService() {
        this.accountService = new AccountService();
        this.cashDispenserService = new ConcurrentHashMap<>();

        this.accountService.createAccount("1001", "1234", new BigDecimal("1000"));
        this.accountService.createAccount("1002", "0000", new BigDecimal("2000"));
    }

    public CardSession authenticate(String atmKey, String cardNumber, String pin) {
        Account account = accountService.getAccount(cardNumber).orElseThrow(() -> new RuntimeException("Account not found"));
        // simple for now
        if (!accountService.verifyAccount(account, pin)) throw new RuntimeException("Invalid pin");

        return new CardSession(atmKey, account.getAccountNumber(), cardNumber);
    }

    public void endSession(CardSession cardSession) {
        //clear session data. log data.
    }


    public WithdrawResult withdraw(CardSession cardSession, BigDecimal amount) {
        Account account = accountService.getAccount(
                cardSession.getAccountNumber()
        ).orElseThrow(() -> new RuntimeException("Account not found"));
        // ========== lock the account. ==========
        if (account.getBalance().compareTo(amount) < 0) return WithdrawResult.fail("Insufficient balance");

        CashDispenserService atmCashDispenser = cashDispenserService.get(cardSession.getAtmKey());

        // optimistic state change
        accountService.debit(account, amount);

        List<Integer> dispensed = atmCashDispenser.dispense(amount.intValue());

        if (dispensed == null) {
            accountService.credit(account, amount);
            return WithdrawResult.fail("Atm cant dispense request amount with available bill");
        }

        BigDecimal balance = accountService.getBalance(account);
        // record transaction

        return WithdrawResult.success("done", dispensed, balance);

        // ========== lock the account. ========== in a finally block
    }

    public void deposit(CardSession cardSession, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new RuntimeException("Deposit amount must be greater than zero");
        Account account = accountService.getAccount(cardSession.getAccountNumber())
                .orElseThrow(() -> new RuntimeException("Account not found"));
        accountService.credit(account, amount);
        //record transaction
    }


    public void registerAtm(String atmKey) {
        CashDispenserService cashDispenser = new CashDispenserService();
        cashDispenser.load(
                // value , count
                Map.of(
                        100, 50,
                        50, 10,
                        10, 500
                )
        );
        cashDispenserService.put(atmKey, cashDispenser);
    }
}
