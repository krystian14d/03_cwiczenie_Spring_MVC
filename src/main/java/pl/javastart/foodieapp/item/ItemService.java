package pl.javastart.foodieapp.item;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    Optional<Item> findItemByNameIgnoreCase(String name) {
        return itemRepository.findByNameIgnoreCase(name);
    }
}
