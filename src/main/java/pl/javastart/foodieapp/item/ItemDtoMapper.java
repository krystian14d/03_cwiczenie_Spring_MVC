package pl.javastart.foodieapp.item;

public class ItemDtoMapper {

    public static ItemDto fromEntity(Item item) {
        return new ItemDto(item.getId(),
                item.getPrice(),
                item.getName(),
                item.getShortDescription(),
                item.getDescription(),
                item.getImgUrl());
    }

    public static Item toEntity(ItemDto itemDto){
        return new Item();
    }
}
