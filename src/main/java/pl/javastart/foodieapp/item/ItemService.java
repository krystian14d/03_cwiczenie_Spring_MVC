package pl.javastart.foodieapp.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public String findItemByNameIgnoreCase(String name, Model model) {
        Optional<Item> foundItem = itemRepository.findByNameIgnoreCase(name.replaceAll("-", " "));
        foundItem.ifPresent(item -> model.addAttribute("item", item));
        return foundItem.map(item -> "item").orElse("redirect:/");
    }

    @Transactional(readOnly = true)
    public Optional<ItemDto> findItemById(Long id) {
        Optional<Item> foundItem = itemRepository.findById(id);
        return mapAndReturnItemDto(foundItem);
    }

    private Optional<ItemDto> mapAndReturnItemDto(Optional<Item> foundItem) {
        if (foundItem.isEmpty()) {
            return Optional.empty();
        }
        Item item = foundItem.get();
        ItemDto itemDto = ItemDtoMapper.map(item);
        return Optional.of(itemDto);
    }
}
