package pl.javastart.foodieapp.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class ItemController {

    private final ItemService itemService;

    //dodać request mapping nad klasą kontrolera, dodać w requestmapping /danie/
    //zmienić pathvariable na requestparam zgodnie z zasadami restApi
    //

    @GetMapping("/order/{name}")
    public String getItem(@PathVariable String name, Model model) {
        Optional<ItemDto> itemDto = itemService.findItemByNameIgnoreCase(name.replaceAll("-", " "));
        itemDto.ifPresent(it -> model.addAttribute("item", it));
        return itemDto.map(it -> "item").orElse("redirect:/");
    }
}
