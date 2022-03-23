package pl.javastart.foodieapp.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.javastart.foodieapp.common.Message;
import pl.javastart.foodieapp.item.ItemDto;
import pl.javastart.foodieapp.item.ItemService;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/order")
public class OrderController {

    private final ClientOrder clientOrder;
    private final ItemService itemService;
    private final OrderService orderService;


    @PostMapping("/add")
    public String addItemToOrder(@RequestParam Long itemId, Model model) {
        Optional<ItemDto> item = itemService.findItemById(itemId);
        item.ifPresent(clientOrder::add);
        if (item.isPresent()) {
            model.addAttribute("message", new Message("Dodano", "Do zamówienia dodano: " + item.get().getName()));
        } else {
            model.addAttribute("message", new Message("Nie dodano", "Pobrano błędne id z menu: " + itemId));
        }
        return "message";
    }

    @GetMapping
    public String getCurrentOrder(Model model) {
        model.addAttribute("order", clientOrder.getOrderDto());
        model.addAttribute("sum", clientOrder.sumClientOrderPrice());
        return "order";
    }

    @PostMapping("/realize")
    public String proceedOrder(@RequestParam String address, @RequestParam String telephone, Model model) {
        OrderDto orderDto = clientOrder.getOrderDto();
        orderDto.setAddress(address);
        orderDto.setTelephone(telephone);
        orderService.saveOrder(orderDto);
        clientOrder.clear();
        model.addAttribute("message", new Message("Dziękujemy", "Zamówienie przekazane do realizacji"));
        return "message";
    }
}
