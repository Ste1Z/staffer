package ru.alexanderrogachev.staffer.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alexanderrogachev.staffer.domains.User;
import ru.alexanderrogachev.staffer.models.Shop;
import ru.alexanderrogachev.staffer.models.Staffer;
import ru.alexanderrogachev.staffer.services.RegistrationService;
import ru.alexanderrogachev.staffer.services.ShopServiceImpl;
import ru.alexanderrogachev.staffer.utils.UserValidatorImpl;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/auth")
public class RegistrationController {

    private static final Logger logger = Logger.getLogger(Staffer.class);

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
    public String registrationPage(@ModelAttribute("staffer") Staffer staffer, @ModelAttribute("user") User user,
                                   @ModelAttribute("shopName") Shop shopName, Model model) {
        List<Shop> shopNames = shopService.getAllShops();
        model.addAttribute("shopNames", shopNames);
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Отображение страницы регистрации");
        return "auth/registration";
    }

    //Регистрация
    @PostMapping("/registration")
    public String performRegistration(@Valid Staffer staffer, BindingResult bindingResult, @Valid User user) {
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Начало регистрации");
        if (bindingResult.hasErrors()) return "auth/registration";

        //TODO переделать на выпадающий список филиалов, а затем выпадающий список его магазинов


        //Устанавливаем филиал пользователя на основе филиала выбранного магазина
        Shop shop = shopService.findShopByName(staffer.getHomeShopName());
        staffer.setBranch(shop.getBranch());

        user.setRoleByPosition(staffer.getPosition());
        staffer.setUsersStaffer(user);
        user.setStaffer(staffer);
        //Проверяем введенные пароли перед регистрацией
        if (user.getPassword().equals(user.getConfirmPassword())) {
            userValidator.validate(user, bindingResult);
            registrationService.register(user);
            logger.info("[" + this.getClass().getSimpleName() + "]" + " Регистрация пройдена");
        } else {
            logger.info("[" + this.getClass().getSimpleName() + "]" + " Регистрация не пройдена");
            bindingResult.rejectValue("confirmPassword", "Пароли не совпадают");
            return "auth/registration";
        }
        return "redirect:/auth/login";
    }
}
