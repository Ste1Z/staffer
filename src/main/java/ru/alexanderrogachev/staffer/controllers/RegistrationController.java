package ru.alexanderrogachev.staffer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alexanderrogachev.staffer.domain.User;
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

    private final RegistrationService registrationService;

    private final UserValidatorImpl userValidator;

    private final ShopServiceImpl shopService;

    @Autowired
    public RegistrationController(RegistrationService registrationService, UserValidatorImpl userValidator, ShopServiceImpl shopService) {
        this.registrationService = registrationService;
        this.userValidator = userValidator;
        this.shopService = shopService;
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        List<Shop> shopNames = shopService.getAllShops();
        model.addAttribute("shopNames", shopNames);
        return "auth/registration";
    }

    //TODO Изменить параметр ролей
    //Регистрация
    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("staffer") @Valid Staffer staffer, @ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        staffer.setUsersStaffer(user);
        user.setStaffer(staffer);
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) return "/auth/registration";
        registrationService.register(user);
        return "redirect:/auth/login";
    }
}
