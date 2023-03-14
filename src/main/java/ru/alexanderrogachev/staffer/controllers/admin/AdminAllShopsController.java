package ru.alexanderrogachev.staffer.controllers.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alexanderrogachev.staffer.services.ShopServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminAllShopsController {

    private static final Logger logger = LogManager.getLogger(AdminAllShopsController.class);

    private final ShopServiceImpl shopService;

    public AdminAllShopsController(ShopServiceImpl shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/allShops")
    public String adminAllShopsPage(Model model) {
        model.addAttribute("allShops", shopService.getAllShops());
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Отображение страницы админки со всеми магазинами");
        return "admin/allShops";
    }
}
