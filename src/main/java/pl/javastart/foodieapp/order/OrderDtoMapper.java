package pl.javastart.foodieapp.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.javastart.foodieapp.item.ItemDtoMapper;

import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class OrderDtoMapper {

    public static OrderDto map(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setItems(order.getItems()
                .stream()
                .map(ItemDtoMapper::map)
                .collect(Collectors.toList())
        );
        dto.setAddress(order.getAddress());
        dto.setTelephone(order.getTelephone());
        dto.setStatus(order.getStatus());
        return dto;
    }

    public static Order map(OrderDto dto) {
        Order order = new Order();
        order.setItems(dto.getItems()
                .stream()
                .map(ItemDtoMapper::map)
                .collect(Collectors.toList())
        );
        order.setAddress(dto.getAddress());
        order.setTelephone(dto.getTelephone());
        order.setStatus(dto.getStatus());
        return order;
    }
}
