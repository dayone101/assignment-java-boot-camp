package bootcamp.java.assignment.cart;

import bootcamp.java.assignment.product.Product;
import bootcamp.java.assignment.product.ProductRepository;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @Test
    void findByUserId() {
        // Arrange
        Product product = new Product();
        product.setId(1);
        product.setName("testing");
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        Cart cart = new Cart();
        cart.setUserId(1);
        cart.setProductId(1);
        List<Cart> carts = new ArrayList<>();
        carts.add(cart);
        when(cartRepository.findByUserId(1)).thenReturn(carts);

        // Act
        CartService cartService = new CartService();
        cartService.setCartRepository(cartRepository);
        cartService.setProductRepository(productRepository);
        List<Product> result = cartService.findByUserId(1);

        // Assert
        assertEquals(1, result.size());
        assertEquals("testing", result.get(0).getName());
    }
}