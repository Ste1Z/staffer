package ru.alexanderrogachev.staffer.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alexanderrogachev.staffer.domains.User;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LogManager.getLogger(AuthController.class);

    @GetMapping("/login")
    public String loginPage(@ModelAttribute("user") User user) {
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Отображение страницы авторизации");
        return "auth/login";
    }


}
