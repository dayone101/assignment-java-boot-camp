package bootcamp.java.assignment.order;

import bootcamp.java.assignment.product.Product;
import bootcamp.java.assignment.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private ProductService productService;

    @GetMapping("/order/user-id/{userId}")
    public List<Integer> getOrderByUserId(@PathVariable int userId) {
        return orderService.findByUserId(userId);
    }

    @GetMapping("/order/order-id/{orderId}")
    public OrderResponse getOrderByOrderId(@PathVariable int orderId) {
        ShoppingOrder order = orderService.findByOrderId(orderId);
        List<OrderItem> orderItems = orderItemService.findByOrderId(orderId);
        List<Product> products = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            Product product = productService.findById(orderItem.getProductId());
            products.add(product);
        }

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderId(orderId);
        orderResponse.setUserId(order.getUserId());
        orderResponse.setUsername(order.getUsername());
        orderResponse.setAddress(order.getAddress());
        orderResponse.setItemsCount(order.getItemsCount());
        orderResponse.setProducts(products);
        orderResponse.setTotal(order.getTotal());
        orderResponse.setPaymentMethod(order.getPaymentMethod());
        return orderResponse;
    }
}
