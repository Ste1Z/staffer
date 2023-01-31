package ru.alexanderrogachev.staffer.controllers;

import org.apache.log4j.Logger;
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

    private static final Logger logger = Logger.getLogger(Staffer.class);

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
    public String registrationPage(@ModelAttribute("staffer") Staffer staffer, @ModelAttribute("user") User user,
                                   @ModelAttribute("branch") Branch branch, @ModelAttribute("position") Position position,
                                   Model model) {
        List<Branch> branches = branchService.getAllBranches();
        model.addAttribute("branches", branches);
        List<Shop> shops = shopService.getAllShops();
        model.addAttribute("shops", shops);
        List<Position> positions = positionService.getAllPositions();
        model.addAttribute("positions", positions);
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Отображение страницы регистрации");
        return "auth/registration";
    }

    //Регистрация
    @PostMapping("/registration")
    public String performRegistration(@Valid Staffer staffer, BindingResult stafferBindingResult,
                                      @Valid User user, BindingResult userBindingResult) {
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Начало регистрации");
        if (stafferBindingResult.hasErrors() || userBindingResult.hasErrors()) return "auth/registration";

        user.setRoleByPosition(staffer.getPosition().getPositionName());
        staffer.setUsersStaffer(user);
        user.setStaffer(staffer);
        userValidator.validate(user, userBindingResult);
        registrationService.register(user);
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Регистрация пройдена");
        return "redirect:/auth/login";
    }
}
