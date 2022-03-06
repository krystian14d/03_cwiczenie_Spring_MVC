package pl.javastart.foodieapp.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.javastart.foodieapp.item.ItemDto;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
public class OrderPanelController {

    private OrderService orderService;

    @GetMapping("/panel/zamowienia")
    public String getOrders(@RequestParam(required = false) OrderStatus status, Model model) {
        List<OrderDto> orders;
        if (status == null) {
            orders = orderService.findAllOrders();
        } else {
            orders = orderService.findAllOrdersByStatus(status);
        }
        model.addAttribute("orders", orders);
        return "panel/orders";
    }

    @GetMapping("/panel/zamowienie/{id}")
    public String singleOrder(@PathVariable Long id, Model model) {
        Optional<OrderDto> orderDto = orderService.findOrderById(id);
        return orderDto.map(o -> singleOrderPanel(o, model))
                .orElse("redirect:/");
    }

    @PostMapping("/panel/zamowienie/{id}")
    public String changeOrderStatus(@PathVariable Long id, Model model) {
        Optional<OrderDto> order = orderService.findOrderById(id);
        order.ifPresent(
                o -> {
                    o.setStatus(OrderStatus.nextStatus(o.getStatus()));
                    orderService.saveOrder(o);
                }
        );
        return order.map(o -> singleOrderPanel(o, model))
                .orElse("redirect:/");
    }

    private String singleOrderPanel(OrderDto orderDto, Model model) {
        model.addAttribute("order", orderDto);
        model.addAttribute("sum", orderDto.getItems().stream()
                .mapToDouble(ItemDto::getPrice)
                .sum());
        return "panel/order";
    }


}
