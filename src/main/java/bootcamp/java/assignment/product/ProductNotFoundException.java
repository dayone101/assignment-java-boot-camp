package bootcamp.java.assignment.product;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(int id) {
        super(String.valueOf(id));
    }

}
