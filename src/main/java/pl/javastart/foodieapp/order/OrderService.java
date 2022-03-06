package pl.javastart.foodieapp.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDtoMapper orderDtoMapper;

    public void saveOrder(OrderDto orderDto) {
        Order order = OrderDtoMapper.map(orderDto);
        orderRepository.save(order);
    }

    public List<OrderDto> findAllOrders() {
        List<Order> allOrders = orderRepository.findAll();
        return allOrders.stream()
                .map(OrderDtoMapper::map)
                .collect(Collectors.toList());
    }

    public List<OrderDto> findAllOrdersByStatus(OrderStatus status) {
        List<Order> allOrdersByStatus = orderRepository.findAllByStatus(status);
        return allOrdersByStatus.stream()
                .map(OrderDtoMapper::map)
                .collect(Collectors.toList());
    }

    public Optional<OrderDto> findOrderById(Long id) {
        Optional<Order> foundOrder = orderRepository.findById(id);
        if (foundOrder.isEmpty()) {
            return Optional.empty();
        }
        OrderDto orderDto = OrderDtoMapper.map(foundOrder.get());
        return Optional.of(orderDto);
    }
}
