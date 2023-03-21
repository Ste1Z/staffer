package ru.alexanderrogachev.staffer.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.alexanderrogachev.staffer.domains.User;
import ru.alexanderrogachev.staffer.models.Staffer;
import ru.alexanderrogachev.staffer.services.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class MyProfileController {

    private static final Logger logger = LogManager.getLogger(MyProfileController.class);

    private final UserDetailsServiceImpl userDetailsService;
    private final StafferServiceImpl stafferService;
    private final ShopServiceImpl shopService;
    private final PositionServiceImpl positionService;
    private final BranchServiceImpl branchService;

    @Autowired
    public MyProfileController(UserDetailsServiceImpl userDetailsService, StafferServiceImpl stafferService, ShopServiceImpl shopService, PositionServiceImpl positionService, BranchServiceImpl branchService) {
        this.userDetailsService = userDetailsService;
        this.stafferService = stafferService;
        this.shopService = shopService;
        this.positionService = positionService;
        this.branchService = branchService;
    }

    //Подгрузка данных пользователя на основе залогиненного пользователя
    @GetMapping("/myProfile")
    public String myProfilePage(HttpServletRequest http, Model model) {
        Staffer currentStaffer = userDetailsService.getStafferFromLoggedUser(http);
        model.addAttribute("currentStaffer", currentStaffer);
        model.addAttribute("allBranches", branchService.getAllBranches());
        model.addAttribute("allShops", shopService.getAllShops());
        model.addAttribute("allPositions", positionService.getAllPositions());
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Отображение страницы профиля");
        return "myProfile";
    }

    //Сохранение измененных данных пользователя
    @PostMapping("/myProfile")
    public String saveProfileChanges(@Valid Staffer staffer, BindingResult bindingResult, HttpServletRequest http) {
        if (bindingResult.hasErrors()) return "/myProfile";
        Principal principal = http.getUserPrincipal();
        User user = userDetailsService.findUserByUsername(principal.getName()).get();
        staffer.setStafferId(user.getStaffer().getStafferId());
        staffer.setUsersStaffer(user);
        stafferService.saveStaffer(staffer);
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Сохранение данных профиля");
        return "redirect:/main";
    }


}
