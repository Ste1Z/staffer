package ru.alexanderrogachev.staffer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alexanderrogachev.staffer.models.Shop;
import ru.alexanderrogachev.staffer.repositories.ShopRepository;

import java.util.List;

@Controller
public class ShopController {

    @Autowired
    private ShopRepository shopRepository;

    //Отображение списка магазинов и фильтрация
    @GetMapping("/shops")
    public String shops(@RequestParam(required = false) String filter, Model model) {
        List<Shop> shops = shopRepository.findAll();
        List<Shop> filterShops;
        if (filter != null && !filter.isEmpty()) {
            filterShops = shopRepository.findByName(filter);
        } else {
            filterShops = shops;
        }
        model.addAttribute("filterShops", filterShops);
        return "shops";
    }

    //Добавление нового магазина
    @PostMapping("/shops")
    public String add(@RequestParam String name, @RequestParam int code, Model model) {
        Shop shop = new Shop(name, code);
        shopRepository.save(shop);
        Iterable<Shop> shops = shopRepository.findAll();
        model.addAttribute("shops", shops);
        return "shops";
    }

}
