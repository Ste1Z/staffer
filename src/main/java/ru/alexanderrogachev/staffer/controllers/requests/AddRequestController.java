package ru.alexanderrogachev.staffer.controllers.requests;

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
import ru.alexanderrogachev.staffer.models.*;
import ru.alexanderrogachev.staffer.services.PositionServiceImpl;
import ru.alexanderrogachev.staffer.services.RequestServiceImpl;
import ru.alexanderrogachev.staffer.services.ShopServiceImpl;
import ru.alexanderrogachev.staffer.services.UserDetailsServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


//Страница создания заявок
@Controller
@RequestMapping("/requests")
public class AddRequestController {

    private static final Logger logger = LogManager.getLogger(AddRequestController.class);

    private final UserDetailsServiceImpl userDetailsService;
    private final RequestServiceImpl requestService;
    private final PositionServiceImpl positionService;
    private final ShopServiceImpl shopService;

    @Autowired
    public AddRequestController(UserDetailsServiceImpl userDetailsService, RequestServiceImpl requestService, PositionServiceImpl positionService, ShopServiceImpl shopService) {
        this.userDetailsService = userDetailsService;
        this.requestService = requestService;
        this.positionService = positionService;
        this.shopService = shopService;
    }

    //Отображение страницы добавления заявки
    @GetMapping("/addRequest")
    public String addRequestPage(@ModelAttribute("request") Request request, Model model, HttpServletRequest http) {
        Staffer currentStaffer = userDetailsService.getStafferFromLoggedUser(http);
        request.setRequestShop(currentStaffer.getStafferShop());
        model.addAttribute("allPositions", positionService.getAllPositions());
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Отображение страницы добавления заявки");
        return "requests/addRequest";
    }

    //Добавление новой заявки
    @PostMapping("/addRequest")
    public String addRequest(@Valid Request request, BindingResult requestBindingResult,
                             Model model, HttpServletRequest http) {
        if (requestBindingResult.hasErrors()) {
            Staffer currentStaffer = userDetailsService.getStafferFromLoggedUser(http);
            model.addAttribute("currentShop", currentStaffer.getStafferShop());
            model.addAttribute("allPositions", positionService.getAllPositions());
            return "requests/addRequest";
        }
        requestService.autoSetDateOfRequest(request);
        requestService.saveRequest(request);
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Заявка успешно создана");
        return "redirect:/requests/allRequest";
    }
}
