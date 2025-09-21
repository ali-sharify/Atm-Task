package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Ali Sharifi
 * <p>ali.sharifi.dev@gmail.com<p>
 */
public class Transaction {
    private TransactionType type;
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private String note;

    public Transaction(TransactionType type, BigDecimal amount, LocalDateTime timestamp, String note) {
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
        this.note = note;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
