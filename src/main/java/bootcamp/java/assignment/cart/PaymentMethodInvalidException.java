package bootcamp.java.assignment.cart;

public class PaymentMethodInvalidException extends RuntimeException{
    public PaymentMethodInvalidException(String paymentMethod) {
        super(paymentMethod);
    }
}
