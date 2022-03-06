package pl.javastart.foodieapp.order;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void saveOrder(Order order){
        orderRepository.save(order);
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> findAllOrdersByStatus(OrderStatus status) {
        return orderRepository.findAllByStatus(status);
    }

    public Optional<Order> findOrderById(Long id) {
        return orderRepository.findById(id);
    }
}
