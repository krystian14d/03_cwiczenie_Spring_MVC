package pl.javastart.foodieapp.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.javastart.foodieapp.item.Item;
import pl.javastart.foodieapp.item.ItemRepository;

import java.util.List;

@Controller
@ComponentScan
public class HomeController {

    private ItemRepository itemRepository;

    public HomeController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @RequestMapping("/")
    public String home(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "index";
    }
}
