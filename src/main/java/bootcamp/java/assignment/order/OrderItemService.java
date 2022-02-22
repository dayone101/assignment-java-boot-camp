package bootcamp.java.assignment.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public void saveOrderItem(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    public List<OrderItem> findByOrderId(int orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }
}
