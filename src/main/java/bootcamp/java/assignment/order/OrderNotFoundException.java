package bootcamp.java.assignment.order;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(int orderId) {
        super(String.valueOf(orderId));
    }
}
