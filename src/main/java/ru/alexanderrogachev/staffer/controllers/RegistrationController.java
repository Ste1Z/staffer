package ru.alexanderrogachev.staffer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alexanderrogachev.staffer.domain.User;
import ru.alexanderrogachev.staffer.models.Staffer;
import ru.alexanderrogachev.staffer.services.RegistrationService;
import ru.alexanderrogachev.staffer.services.StafferServiceImpl;
import ru.alexanderrogachev.staffer.utils.UserValidatorImpl;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class RegistrationController {

    private final RegistrationService registrationService;

    private final UserValidatorImpl userValidator;

    @Autowired
    public RegistrationController(RegistrationService registrationService, UserValidatorImpl userValidator) {
        this.registrationService = registrationService;
        this.userValidator = userValidator;
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "auth/registration";
    }

    //TODO Изменить параметр ролей
    //Регистрация
    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("staffer") Staffer staffer, @ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        staffer.setUsersStaffer(user);
        user.setStaffer(staffer);
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) return "/auth/registration";
        registrationService.register(user);
        return "redirect:/auth/login";
    }
}
