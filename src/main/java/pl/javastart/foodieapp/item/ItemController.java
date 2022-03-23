package pl.javastart.foodieapp.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/course")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/{name}")
    public String getItem(@PathVariable String name, Model model) {
        return itemService.findItemByNameIgnoreCase(name, model);
    }
}
