package ru.alexanderrogachev.staffer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.alexanderrogachev.staffer.models.Request;
import ru.alexanderrogachev.staffer.models.Shop;
import ru.alexanderrogachev.staffer.models.Staffer;
import ru.alexanderrogachev.staffer.services.RequestServiceImpl;
import ru.alexanderrogachev.staffer.services.ShopServiceImpl;
import ru.alexanderrogachev.staffer.services.StafferServiceImpl;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ShopServiceImpl shopService;

    private final StafferServiceImpl stafferService;

    private final RequestServiceImpl requestService;

    @Autowired
    public AdminController(ShopServiceImpl shopService, StafferServiceImpl stafferService, RequestServiceImpl requestService) {
        this.shopService = shopService;
        this.stafferService = stafferService;
        this.requestService = requestService;
    }

    //________________________________________
    //Страница администратора сайта
    //________________________________________


    @GetMapping("panel")
    public String adminPage() {
        return "admin/panel";
    }

    //________________________________________
    //Страница управления заявками
    //________________________________________


    //Отображение списка запросов и фильтрация
    @GetMapping("/all_requests")
    public String requestsPage(@RequestParam(required = false) String filter, Model model) {
        List<Request> requests = requestService.getAllRequests();
        List<Request> filterRequests;
        if (filter != null && !filter.isEmpty()) {
            filterRequests = requestService.findRequestByShopName(filter);
        } else {
            filterRequests = requests;
        }
        model.addAttribute("filterRequests", filterRequests);
        return "admin/all_requests";
    }

    //________________________________________
    //Страница управления магазинами
    //________________________________________

    //Отображение списка магазинов и фильтрация
    @GetMapping("shops")
    public String shops(@RequestParam(required = false) String filter, Model model) {
        List<Shop> shops = shopService.getAllShops();
        List<Shop> filterShops;
        if (filter != null && !filter.isEmpty()) {
            filterShops = shopService.findShopsByName(filter);
        } else {
            filterShops = shops;
        }
        model.addAttribute("filterShops", filterShops);
        return "admin/shops";
    }

    //Добавление нового магазина
    @PostMapping("shops")
    public String addShop(@ModelAttribute("shop") @Valid Shop shop) {
        shopService.saveShop(shop);
        return "admin/shops";
    }

    //________________________________________
    //Страница управления сотрудниками
    //________________________________________

    //Отображение списка сотрудников и фильтрация
    @GetMapping("staffers")
    public String staffersPage(@RequestParam(required = false) String filter, Model model) {
        List<Staffer> staffers = stafferService.getAllStaffers();
        List<Staffer> filterStaffers;
        if (filter != null && !filter.isEmpty()) {
            filterStaffers = stafferService.findStafferByName(filter);
        } else {
            filterStaffers = staffers;
        }
        model.addAttribute("filterStaffers", filterStaffers);
        return "admin/staffers";
    }

    //Добавление нового сотрудника
    @PostMapping("staffers")
    public String add(@ModelAttribute("staffer") @Valid Staffer staffer) {
        stafferService.saveStaffer(staffer);
        return "admin/staffers";
    }

}
