package bootcamp.java.assignment.cart;

public class BalanceInsufficientException extends RuntimeException{
    public BalanceInsufficientException(String detail) {
        super(detail);
    }
}
