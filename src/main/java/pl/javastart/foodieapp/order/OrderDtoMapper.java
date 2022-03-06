package pl.javastart.foodieapp.order;

import pl.javastart.foodieapp.item.ItemDtoMapper;

public class OrderDtoMapper {

    public static OrderDto fromEntity(Order order){
        return new OrderDto(order.getId(),
                order.getItems(),
                order.getAddress(),
                order.getTelephone(),
                order.getStatus());
    }

    public static Order toEntity (OrderDto orderDto){
        return null;
    }
}
