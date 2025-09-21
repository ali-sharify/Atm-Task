package service;

import model.CardSession;
import model.WithdrawResult;

import java.math.BigDecimal;

/**
 * @author Ali Sharifi
 * <p>ali.sharifi.dev@gmail.com<p>
 */
public class AtmService {
    private static final String ATM_KEY = "ATM";

    private static final BankService bankService = new BankService();

    private CardSession cardSession;

    public AtmService() {
        bankService.registerAtm(ATM_KEY);
    }

    public boolean authenticate(String cardNumber, String pin) {
        try {
            cardSession = bankService.authenticate(ATM_KEY, cardNumber, pin);
            return true;
        } catch (RuntimeException e) {//todo change this to atm exception
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void endSession() {
        bankService.endSession(cardSession);
        cardSession = null;
    }

    public WithdrawResult withdraw(BigDecimal amount) {
        if (cardSession == null) throw new RuntimeException("Invalid session");
        if (amount.signum() <= 0) throw new RuntimeException("Invalid amount, negative value");
        if (amount.scale() > 0) throw new RuntimeException("Invalid amount, only integer value is allowed");

        return bankService.withdraw(cardSession, amount);
    }
}
