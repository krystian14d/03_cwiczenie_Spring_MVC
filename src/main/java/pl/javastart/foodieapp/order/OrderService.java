package pl.javastart.foodieapp.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
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
