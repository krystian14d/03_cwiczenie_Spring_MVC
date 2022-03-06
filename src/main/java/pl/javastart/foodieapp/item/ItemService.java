package pl.javastart.foodieapp.item;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemDtoMapper itemDtoMapper;

    Optional<ItemDto> findItemByNameIgnoreCase(String name) {
        Optional<Item> foundItem = itemRepository.findByNameIgnoreCase(name);
        return mapAndReturnItemDto(foundItem);
    }

    public Optional<ItemDto> findItemById(Long id) {
        Optional<Item> foundItem = itemRepository.findById(id);
        return mapAndReturnItemDto(foundItem);
    }

    private Optional<ItemDto> mapAndReturnItemDto(Optional<Item> foundItem) {
        if (foundItem.isEmpty()) {
            return Optional.empty();
        }
        Item item = foundItem.get();
        ItemDto itemDto = itemDtoMapper.map(item);
        return Optional.of(itemDto);
    }
}
