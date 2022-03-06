package pl.javastart.foodieapp.item;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/danie/{name}")
    public String getItem(@PathVariable String name, Model model) {
        Optional<ItemDto> itemDto = itemService.findItemByNameIgnoreCase(name.replaceAll("-", " "));
        itemDto.ifPresent(it -> model.addAttribute("item", it));
        return itemDto.map(it -> "itemDto").orElse("redirect:/");
    }
}
