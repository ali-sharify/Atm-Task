package model;

/**
 * @author Ali Sharifi
 * <p>ali.sharifi.dev@gmail.com<p>
 */
public class CardSession {
    private String atmKey;
    private String accountNumber;
    private String cardNumber;

    public CardSession(String atmKey, String accountNumber, String cardNumber) {
        this.atmKey = atmKey;
        this.accountNumber = accountNumber;
        this.cardNumber = cardNumber;
    }

    public String getAtmKey() {
        return atmKey;
    }

    public void setAtmKey(String atmKey) {
        this.atmKey = atmKey;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
