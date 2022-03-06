package pl.javastart.foodieapp.item;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    Optional<ItemDto> findItemByNameIgnoreCase(String name) {
        Item foundItem = itemRepository.findByNameIgnoreCase(name);
        return Optional.of(ItemDtoMapper.fromEntity(foundItem));
    }

    public Optional<ItemDto> findItemById(Long id){
        Optional<Item> foundItem = itemRepository.findById(id);
        ItemDto itemDto = ItemDtoMapper.fromEntity(foundItem.get());
        return Optional.of(itemDto);
    }
}
