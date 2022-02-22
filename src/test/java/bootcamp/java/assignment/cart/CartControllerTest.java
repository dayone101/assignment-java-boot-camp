package bootcamp.java.assignment.cart;

import bootcamp.java.assignment.order.*;
import bootcamp.java.assignment.product.Product;
import bootcamp.java.assignment.user.User;
import bootcamp.java.assignment.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CartControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private CartService cartService;

    @MockBean
    private UserService userService;

    @MockBean
    private OrderService orderService;

    @Test
    void findByUserId_has_items_in_cart() {
        // Arrange
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        when(cartService.findByUserId(1)).thenReturn(products);

        // Act
        CartResponse result = testRestTemplate.getForObject("/cart/1", CartResponse.class);

        // Assert
        assertEquals(2, result.getItemsCount());
    }

    @Test
    void findByUserId_empty_cart() {
        // Arrange
        List<Product> products = new ArrayList<>();
        when(cartService.findByUserId(1)).thenReturn(products);

        // Act
        CartResponse result = testRestTemplate.getForObject("/cart/1", CartResponse.class);

        // Assert
        assertEquals(0, result.getItemsCount());
    }

    @Test
    void addProduct() {
        // Arrange
        Cart cart = new Cart();

        // Act
        String result = testRestTemplate.postForObject("/cart/add", cart, String.class);

        // Assert
        assertEquals("item added", result);
    }

    @Test
    void checkout() {
        // Arrange
        CheckoutInfo checkoutInfo = new CheckoutInfo();
        checkoutInfo.setUserId(1);
        checkoutInfo.setPaymentMethod("wallet");

        User user = new User();
        user.setId(1);
        user.setWallet(500);
        when(userService.findById(1)).thenReturn(user);

        Product product = new Product();
        product.setId(1);
        product.setPrice(10);
        List<Product> products = new ArrayList<>();
        products.add(product);
        when(cartService.findByUserId(1)).thenReturn(products);

        when(orderService.saveOrder(any(ShoppingOrder.class))).thenReturn(new ShoppingOrder());

        // Act
        ResponseEntity<OrderResponse> response = testRestTemplate
                .postForEntity("/cart/checkout",
                        checkoutInfo, OrderResponse.class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}