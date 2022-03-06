package pl.javastart.foodieapp.order;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.javastart.foodieapp.item.ItemDto;

@Component
@SessionScope
public class ClientOrder {

    private OrderDto orderDto;

    public ClientOrder() {
        clear();
    }

    public OrderDto getOrderDto() {
        return orderDto;
    }

    void add(ItemDto itemDto) {
        orderDto.getItems().add(itemDto);
    }

    void clear() {
        orderDto = new OrderDto();
        orderDto.setStatus(OrderStatus.NEW);
    }
}
