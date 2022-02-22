package bootcamp.java.assignment.order;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public ShoppingOrder saveOrder(ShoppingOrder order) {
        return orderRepository.saveAndFlush(order);
    }

    public ShoppingOrder findByOrderId(int orderId) {
        Optional<ShoppingOrder> result = orderRepository.findById(orderId);
        if (result.isPresent()) {
            return result.get();
        }

        throw new OrderNotFoundException(orderId);
    }

    public List<Integer> findByUserId(int userId) {
        List<ShoppingOrder> orders = orderRepository.findByUserId(userId);
        List<Integer> orderIds = new ArrayList<>();
        for (ShoppingOrder order : orders) {
            orderIds.add(order.getId());
        }

        return orderIds;
    }
}
