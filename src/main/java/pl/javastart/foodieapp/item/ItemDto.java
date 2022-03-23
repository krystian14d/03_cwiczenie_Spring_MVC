package pl.javastart.foodieapp.item;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemDto {

    private Long id;
    private Double price;
    private String name;
    private String shortDescription;
    private String description;
    private String imgUrl;
}
