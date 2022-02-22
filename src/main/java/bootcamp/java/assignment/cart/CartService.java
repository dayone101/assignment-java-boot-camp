package bootcamp.java.assignment.cart;

import bootcamp.java.assignment.product.Product;
import bootcamp.java.assignment.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    public void setCartRepository(CartRepository  cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findByUserId(int userId) {
        List<Cart> carts = cartRepository.findByUserId(userId);
        List<Product> products = new ArrayList<>();
        for (Cart cart: carts) {
            Optional<Product> product =
                    productRepository.findById(cart.getProductId());
            product.ifPresent(products::add);
        }

        return products;
    }

    public void addProduct(Cart cart) {
        cartRepository.save(cart);
    }

    public void deleteByUserId(int userId) {
        cartRepository.removeByUserId(userId);
    }
}
