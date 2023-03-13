package ru.alexanderrogachev.staffer.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alexanderrogachev.staffer.domains.User;
import ru.alexanderrogachev.staffer.models.Branch;
import ru.alexanderrogachev.staffer.models.Position;
import ru.alexanderrogachev.staffer.models.Shop;
import ru.alexanderrogachev.staffer.models.Staffer;
import ru.alexanderrogachev.staffer.services.BranchServiceImpl;
import ru.alexanderrogachev.staffer.services.PositionServiceImpl;
import ru.alexanderrogachev.staffer.services.RegistrationService;
import ru.alexanderrogachev.staffer.services.ShopServiceImpl;
import ru.alexanderrogachev.staffer.utils.UserValidatorImpl;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/auth")
public class RegistrationController {

    private static final Logger logger = LogManager.getLogger(RegistrationController.class);

    private final RegistrationService registrationService;
    private final UserValidatorImpl userValidator;
    private final ShopServiceImpl shopService;
    private final BranchServiceImpl branchService;
    private final PositionServiceImpl positionService;


    @Autowired
    public RegistrationController(RegistrationService registrationService, UserValidatorImpl userValidator, ShopServiceImpl shopService, BranchServiceImpl branchService, PositionServiceImpl positionService) {
        this.registrationService = registrationService;
        this.userValidator = userValidator;
        this.shopService = shopService;
        this.branchService = branchService;
        this.positionService = positionService;
    }

    //Отображение страницы регистрации
    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("staffer") Staffer staffer, @ModelAttribute("user") User user, Model model) {
        model.addAttribute("allBranches", branchService.getAllBranches());
        model.addAttribute("allShops", shopService.getAllShops());
        model.addAttribute("allPositions", positionService.getAllPositions());
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Отображение страницы регистрации");
        return "auth/registration";
    }

    //Регистрация
    @PostMapping("/registration")
    public String performRegistration(@Valid Staffer staffer, BindingResult stafferBindingResult,
                                      @Valid User user, BindingResult userBindingResult,
                                      Model model) {
        if (stafferBindingResult.hasErrors() || userBindingResult.hasErrors()) {
            model.addAttribute("allBranches", branchService.getAllBranches());
            model.addAttribute("allShops", shopService.getAllShops());
            model.addAttribute("allPositions", positionService.getAllPositions());
            return "auth/registration";
        }
        user.setRoleByPosition(staffer.getStafferPosition().getPositionName());
        staffer.setUsersStaffer(user);
        user.setStaffer(staffer);
        userValidator.validate(user, userBindingResult);
        registrationService.register(user);
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Регистрация пройдена");
        return "redirect:/auth/login";
    }
}
