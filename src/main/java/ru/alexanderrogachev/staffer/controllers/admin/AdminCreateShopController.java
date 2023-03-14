package ru.alexanderrogachev.staffer.controllers.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alexanderrogachev.staffer.models.Shop;
import ru.alexanderrogachev.staffer.services.BranchServiceImpl;
import ru.alexanderrogachev.staffer.services.ShopServiceImpl;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminCreateShopController {
    private static final Logger logger = LogManager.getLogger(AdminCreateShopController.class);

    private final ShopServiceImpl shopService;
    private final BranchServiceImpl branchService;

    @Autowired
    public AdminCreateShopController(ShopServiceImpl shopService, BranchServiceImpl branchService) {
        this.shopService = shopService;
        this.branchService = branchService;
    }

    @GetMapping("/createShop")
    public String adminCreateShopPage(@ModelAttribute("shop") Shop shop, Model model) {
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Отображение страницы админки со созданием магазина");
        model.addAttribute("allBranches", branchService.getAllBranches());
        return "admin/createShop";
    }

    @PostMapping("/createShop")
    public String createShop(@Valid Shop shop, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("allBranches", branchService.getAllBranches());
            return "admin/createShop";
        }
        shopService.saveShop(shop);
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Магазин успешно создан");
        return "redirect:/admin/allShops";
    }
}
