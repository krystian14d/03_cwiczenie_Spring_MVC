package pl.javastart.foodieapp.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/panel")
public class OrderPanelController {

    private final OrderService orderService;

    @GetMapping("/orders")
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

    @GetMapping("/order/{id}")
    public String displaySingleOrder(@PathVariable Long id, Model model) {
        return orderService.displaySingleOrder(id, model);
    }

    @PostMapping("/order/{id}")
    public String changeOrderStatus(@PathVariable Long id, Model model) {
        return orderService.changeOrderStatus(id, model);
    }

}
