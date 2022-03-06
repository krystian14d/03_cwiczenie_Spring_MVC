package pl.javastart.foodieapp.order;

import lombok.Getter;
import lombok.Setter;
import pl.javastart.foodieapp.item.ItemDto;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderDto {

    private Long id;
    private List<ItemDto> items = new ArrayList<>();
    private String address;
    private String telephone;
    private OrderStatus status;
}
