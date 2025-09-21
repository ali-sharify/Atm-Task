package model;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Ali Sharifi
 * <p>ali.sharifi.dev@gmail.com<p>
 */
public class WithdrawResult {
    private boolean success;
    private String message;
    private List<Integer> dispensed;// it can be a map
    private BigDecimal remaining;

    public WithdrawResult() {
    }

    public static WithdrawResult fail(String message) {
        return new WithdrawResult(false, message, null, null);
    }

    public static WithdrawResult success(String message, List<Integer> dispensed, BigDecimal remaining) {
        return new WithdrawResult(true, message, dispensed, remaining);
    }

    public WithdrawResult(boolean success, String message, List<Integer> dispensed, BigDecimal remaining) {
        this.success = success;
        this.message = message;
        this.dispensed = dispensed;
        this.remaining = remaining;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Integer> getDispensed() {
        return dispensed;
    }

    public void setDispensed(List<Integer> dispensed) {
        this.dispensed = dispensed;
    }

    public BigDecimal getRemaining() {
        return remaining;
    }

    public void setRemaining(BigDecimal remaining) {
        this.remaining = remaining;
    }
}
