package ru.alexanderrogachev.staffer.controllers.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminMainController {

    private static final Logger logger = LogManager.getLogger(AdminMainController.class);

    @GetMapping("/main")
    public String adminMainPage() {
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Отображение страницы авторизации");
        return "admin/main";
    }
}
