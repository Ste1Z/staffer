package ru.alexanderrogachev.staffer.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.alexanderrogachev.staffer.controllers.requests.AddRequestController;
import ru.alexanderrogachev.staffer.domains.User;
import ru.alexanderrogachev.staffer.models.Branch;
import ru.alexanderrogachev.staffer.models.Position;
import ru.alexanderrogachev.staffer.models.Shop;
import ru.alexanderrogachev.staffer.models.Staffer;
import ru.alexanderrogachev.staffer.services.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class MyProfileController {

    private static final Logger logger = LogManager.getLogger(AddRequestController.class);

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
    public String myProfilePage(HttpServletRequest http, @ModelAttribute("branch") Branch branch, @ModelAttribute("shop") Shop shop,
                                @ModelAttribute("position") Position position, Model model) {
        Principal principal = http.getUserPrincipal();
        User user = userDetailsService.findUserByUsername(principal.getName()).get();
        model.addAttribute("staffer", user.getStaffer());
        model.addAttribute("branches", branchService.getAllBranches());
        model.addAttribute("shops", shopService.getAllShops());
        model.addAttribute("positions", positionService.getAllPositions());
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Отображение страницы профиля");
        return "myProfile";
    }

    //Сохранение измененных данных пользователя
    @PostMapping("/myProfile")
    public String saveProfileChanges(HttpServletRequest http, @ModelAttribute("staffer") @Valid Staffer staffer) {
        Principal principal = http.getUserPrincipal();
        User user = userDetailsService.findUserByUsername(principal.getName()).get();
        staffer.setStafferId(user.getStaffer().getStafferId());
        staffer.setUsersStaffer(user);
        //TODO проверить на работоспособность
        staffer.checkChangesOfStafferProfileAndSetPreviousValues(user.getStaffer());
        stafferService.saveStaffer(staffer);
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Сохранение данных профиля");
        return "redirect:/main";
    }


}
