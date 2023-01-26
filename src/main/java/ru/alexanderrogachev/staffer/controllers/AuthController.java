package ru.alexanderrogachev.staffer.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alexanderrogachev.staffer.domains.User;
import ru.alexanderrogachev.staffer.models.Staffer;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = Logger.getLogger(Staffer.class);

    @GetMapping("/login")
    public String loginPage(@ModelAttribute("user") User user) {
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Отображение страницы авторизации");
        return "auth/login";
    }



}
