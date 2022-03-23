package pl.javastart.foodieapp.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import pl.javastart.foodieapp.item.Item;

import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;
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

    @Transactional(readOnly = true)
    public List<OrderDto> findAllOrders() {
        List<Order> allOrders = orderRepository.findAll();
        return allOrders.stream()
                .map(OrderDtoMapper::map)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<OrderDto> findAllOrdersByStatus(OrderStatus status) {
        List<Order> allOrdersByStatus = orderRepository.findAllByStatus(status);
        return allOrdersByStatus.stream()
                .map(OrderDtoMapper::map)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public String displaySingleOrder(Long id, Model model) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(o -> singleOrderPanel(o, model))
                .orElse("redirect:/");
    }

    @Transactional
    public String changeOrderStatus(Long id, Model model) {
        return orderRepository.findById(id)
                .map(changeStatus())
                .map(o -> singleOrderPanel(o, model))
                .orElse("redirect:/");
    }

    private UnaryOperator<Order> changeStatus() {
        return this::changeStatus;
    }

    private Order changeStatus(Order order) {
        order.setStatus(OrderStatus.nextStatus(order.getStatus()));
        return order;
    }

    private String singleOrderPanel(Order order, Model model) {
        model.addAttribute("order", order);
        model.addAttribute("sum", order.getItems().stream()
                .mapToDouble(Item::getPrice)
                .sum());
        return "panel/order";
    }
}
