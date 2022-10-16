package ru.alexanderrogachev.staffer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.alexanderrogachev.staffer.models.Shop;
import ru.alexanderrogachev.staffer.services.ShopServiceImpl;

import java.util.Date;
import java.util.List;

@Controller
public class ShopController {

    private final ShopServiceImpl shopService;

    @Autowired
    public ShopController(ShopServiceImpl shopService) {
        this.shopService = shopService;
    }

    //Отображение списка магазинов и фильтрация
    @GetMapping("/shops")
    public String shops(@RequestParam(required = false) String filter, Model model) {
        List<Shop> shops = shopService.getAllShops();
        List<Shop> filterShops;
        if (filter != null && !filter.isEmpty()) {
            filterShops = shopService.findShopByName(filter);
        } else {
            filterShops = shops;
        }
        model.addAttribute("filterShops", filterShops);
        return "shops";
    }

    //Добавление нового магазина
    @PostMapping("/shops")
    public String addShop(@ModelAttribute("shop") Shop shop) {
        shopService.saveShop(shop);
        return "shops";
    }

    //Удаление магазина
    @PostMapping("/shops/{name}")
    public String deleteShop(@PathVariable String name) {
        shopService.deleteShopByName(name);
        return "shops";
    }

}
