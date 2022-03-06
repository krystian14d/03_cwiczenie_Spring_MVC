package pl.javastart.foodieapp.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.javastart.foodieapp.item.Item;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class OrderDto {

    private Long id;
    private List<Item> items = new ArrayList<>();
    private String address;
    private String telephone;
    private OrderStatus status;
}
