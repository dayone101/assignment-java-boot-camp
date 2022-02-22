package bootcamp.java.assignment.order;

import bootcamp.java.assignment.product.Product;
import bootcamp.java.assignment.product.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private OrderService orderService;

    @MockBean
    private OrderItemService orderItemService;

    @MockBean
    private ProductService productService;

    @Test
    void getOrderByOrderId() {
        // Arrange
        Product product = new Product();
        product.setId(1);
        product.setName("zxcvbn");
        product.setPrice(10);
        when(productService.findById(1)).thenReturn(product);

        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(1);
        orderItem.setProductId(1);
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);
        when(orderItemService.findByOrderId(1)).thenReturn(orderItems);

        ShoppingOrder order = new ShoppingOrder();
        order.setId(1);
        order.setPaymentMethod("test");
        when(orderService.findByOrderId(1)).thenReturn(order);

        // Act
        OrderResponse response = testRestTemplate
                .getForObject("/order/order-id/1", OrderResponse.class);

        // Assert
        assertEquals("test", response.getPaymentMethod());
    }
}