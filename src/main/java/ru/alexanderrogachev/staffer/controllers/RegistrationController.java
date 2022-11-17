package ru.alexanderrogachev.staffer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alexanderrogachev.staffer.domain.Role;
import ru.alexanderrogachev.staffer.domain.User;
import ru.alexanderrogachev.staffer.models.Shop;
import ru.alexanderrogachev.staffer.models.Staffer;
import ru.alexanderrogachev.staffer.services.RegistrationService;
import ru.alexanderrogachev.staffer.services.ShopServiceImpl;
import ru.alexanderrogachev.staffer.utils.UserValidatorImpl;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/auth")
public class RegistrationController {

    private final RegistrationService registrationService;

    private final UserValidatorImpl userValidator;

    private final ShopServiceImpl shopService;

    @Autowired
    public RegistrationController(RegistrationService registrationService, UserValidatorImpl userValidator, ShopServiceImpl shopService) {
        this.registrationService = registrationService;
        this.userValidator = userValidator;
        this.shopService = shopService;
    }

    //Отображение страницы регистрации
    @GetMapping("/registration")
    public String registrationPage(Model model, @ModelAttribute("shopName") Shop shopName) {
        List<Shop> shopNames = shopService.getAllShops();
        model.addAttribute("shopNames", shopNames);
        return "auth/registration";
    }

    //Регистрация
    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("staffer") @Valid Staffer staffer, @ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                                      @RequestParam("position") String position) {
        //Устанавливаем филиал пользователя на основе филиала выбранного магазина
        Shop shop = shopService.findShopByName(staffer.getHomeShopName());
        staffer.setBranch(shop.getBranch());

        //Выставляем роль в правах доступа для пользователя на основе выбранной должности
        switch (position) {
            case "Продавец":
            case "Старший продавец":
                user.setUserRole(Collections.singleton(Role.ROLE_STAFFER));
                break;
            case "Товаровед":
            case "Заместитель директора":
            case "Директор":
                user.setUserRole(Collections.singleton(Role.ROLE_SHOP_ADMIN));
                break;
        }

        staffer.setUsersStaffer(user);
        user.setStaffer(staffer);
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) return "/auth/registration";
        registrationService.register(user);
        return "redirect:/auth/login";
    }
}
