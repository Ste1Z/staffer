package ru.alexanderrogachev.staffer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.alexanderrogachev.staffer.domains.User;
import ru.alexanderrogachev.staffer.models.Shop;
import ru.alexanderrogachev.staffer.models.Staffer;
import ru.alexanderrogachev.staffer.services.ShopServiceImpl;
import ru.alexanderrogachev.staffer.services.StafferServiceImpl;
import ru.alexanderrogachev.staffer.services.UserDetailsServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class MyProfileController {

    private final UserDetailsServiceImpl userDetailsService;

    private final StafferServiceImpl stafferService;

    private final ShopServiceImpl shopService;


    @Autowired
    public MyProfileController(UserDetailsServiceImpl userDetailsService, StafferServiceImpl stafferService, ShopServiceImpl shopService) {
        this.userDetailsService = userDetailsService;
        this.stafferService = stafferService;
        this.shopService = shopService;
    }

    //Подгрузка данных пользователя на основе залогиненного пользователя
    @GetMapping("/my_profile")
    public String myProfilePage(HttpServletRequest http, Model model) {
        Principal principal = http.getUserPrincipal();
        User user = userDetailsService.findUserByUsername(principal.getName()).get();
        Staffer staffer = user.getStaffer();
        model.addAttribute("staffer", staffer);
        List<Shop> shopNames = shopService.getAllShops();
        model.addAttribute("shopNames", shopNames);
        String stafferHomeShop = staffer.getHomeShopName();
        model.addAttribute("stafferHomeShop", stafferHomeShop);
        String stafferPosition = staffer.getPosition();
        model.addAttribute("stafferPosition", stafferPosition);
        return "my_profile";
    }

    //Сохранение измененных данных пользователя
    @PostMapping("/my_profile")
    public String saveProfileChanges(HttpServletRequest http, @ModelAttribute("staffer") @Valid Staffer staffer) {
        Principal principal = http.getUserPrincipal();
        User user = userDetailsService.findUserByUsername(principal.getName()).get();
        staffer.setStafferId(user.getStaffer().getStafferId());
        staffer.setUsersStaffer(user);
        //Получаем версию работника на основе залогиненного пользователя и выставляем старые значения, если ничего не выбрано в полях
        Staffer oldVersionOfStaffer = user.getStaffer();
        if (staffer.getHomeShopName() == null || staffer.getHomeShopName().isEmpty()) {
            staffer.setHomeShopName(oldVersionOfStaffer.getHomeShopName());
        }
        if (staffer.getBranch() == null || staffer.getBranch().isEmpty()) {
            staffer.setBranch(oldVersionOfStaffer.getBranch());
        }
        if (staffer.getPosition() == null || staffer.getPosition().isEmpty()) {
            staffer.setPosition(oldVersionOfStaffer.getPosition());
        }
        stafferService.saveStaffer(staffer);
        return "redirect:/main";
    }


}
