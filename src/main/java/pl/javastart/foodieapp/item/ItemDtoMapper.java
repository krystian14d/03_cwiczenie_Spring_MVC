package pl.javastart.foodieapp.item;

import org.springframework.stereotype.Component;

@Component
public class ItemDtoMapper {

    public static ItemDto map(Item item) {
        ItemDto dto = new ItemDto();
        mapItemToDto(item, dto);
        return dto;
    }

    public static Item map(ItemDto dto) {
        Item item = new Item();
        mapDtoToItem(dto, item);
        return item;
    }

    private static void mapDtoToItem(ItemDto dto, Item item) {
        item.setId(dto.getId());
        item.setPrice(dto.getPrice());
        item.setName(dto.getName());
        item.setShortDescription(dto.getShortDescription());
        item.setDescription(dto.getDescription());
        item.setImgUrl(dto.getImgUrl());
    }

    private static void mapItemToDto(Item item, ItemDto dto) {
        dto.setId(item.getId());
        dto.setPrice(item.getPrice());
        dto.setName(item.getName());
        dto.setShortDescription(item.getShortDescription());
        dto.setDescription(item.getDescription());
        dto.setImgUrl(item.getImgUrl());
    }
}
