package bootcamp.java.assignment.cart;

import bootcamp.java.assignment.order.*;
import bootcamp.java.assignment.product.Product;
import bootcamp.java.assignment.user.User;
import bootcamp.java.assignment.user.UserIdNotFoundException;
import bootcamp.java.assignment.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/cart/{userId}")
    public CartResponse findByUserId(@PathVariable int userId) {
        return new CartResponse(cartService.findByUserId(userId));
    }

    @PostMapping("/cart/add")
    public String addProduct(@RequestBody Cart cart) {
        cartService.addProduct(cart);
        return "item added";
    }

    @PostMapping("/cart/checkout")
    public OrderResponse checkout(@RequestBody CheckoutInfo checkoutInfo) {
        String paymentMethod = checkoutInfo.getPaymentMethod().toLowerCase(Locale.ROOT);
        User user = userService.findById(checkoutInfo.getUserId());

        CartResponse cart = new CartResponse(cartService.findByUserId(checkoutInfo.getUserId()));
        if (cart.getItemsCount() == 0)
            throw new CartEmptyException();

        if (!paymentMethod.equals("wallet") &&
        !paymentMethod.equals("credit card") &&
        !paymentMethod.equals("cash on delivery")) {
            throw new PaymentMethodInvalidException(paymentMethod);
        }

        if (paymentMethod.equals("wallet")) {
            if (user.getWallet() < cart.getTotal())
                throw new BalanceInsufficientException("total: " + cart.getTotal()
                        + ", remaining balance: " + user.getWallet());

            user.setWallet(user.getWallet() - cart.getTotal());
        }

        ShoppingOrder savedOrder = saveOrder(user, cart, paymentMethod);
        saveOrderItems(savedOrder.getId(), cart.getProducts());
        cartService.deleteByUserId(user.getId());

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderId(savedOrder.getId());
        orderResponse.setUserId(user.getId());
        orderResponse.setUsername(user.getUsername());
        orderResponse.setAddress(user.getAddress());
        orderResponse.setItemsCount(cart.getItemsCount());
        orderResponse.setProducts(cart.getProducts());
        orderResponse.setTotal(cart.getTotal());
        orderResponse.setPaymentMethod(paymentMethod);
        return orderResponse;
    }

    private ShoppingOrder saveOrder(User user, CartResponse cart, String paymentMethod) {
        ShoppingOrder order = new ShoppingOrder();
        order.setUserId(user.getId());
        order.setUsername(user.getUsername());
        order.setAddress(user.getAddress());
        order.setItemsCount(cart.getItemsCount());
        order.setTotal(cart.getTotal());
        order.setPaymentMethod(paymentMethod);
        return orderService.saveOrder(order);
    }

    @Transactional
    private void saveOrderItems(int orderId, List<Product> products) {
        for (Product product: products) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setProductId(product.getId());
            orderItemService.saveOrderItem(orderItem);
        }
    }
}
