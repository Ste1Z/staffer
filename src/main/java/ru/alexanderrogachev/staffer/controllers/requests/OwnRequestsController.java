package ru.alexanderrogachev.staffer.controllers.requests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alexanderrogachev.staffer.domains.User;
import ru.alexanderrogachev.staffer.models.Request;
import ru.alexanderrogachev.staffer.models.Shop;
import ru.alexanderrogachev.staffer.models.Staffer;
import ru.alexanderrogachev.staffer.services.RequestServiceImpl;
import ru.alexanderrogachev.staffer.services.UserDetailsServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

//Страница с отображением заявок созданных магазином
@Controller
@RequestMapping("/requests")
public class OwnRequestsController {

    private static final Logger logger = LogManager.getLogger(OwnRequestsController.class);

    private final UserDetailsServiceImpl userDetailsService;
    private final RequestServiceImpl requestService;

    @Autowired
    public OwnRequestsController(UserDetailsServiceImpl userDetailsService, RequestServiceImpl requestService) {
        this.userDetailsService = userDetailsService;
        this.requestService = requestService;
    }

    //Отображение страницы со списком заявок пользователя
    @GetMapping("/ownRequests")
    public String myRequestsPage(@RequestParam(required = false) Shop filter, Model model, HttpServletRequest http) {
        Principal principal = http.getUserPrincipal();
        User user = userDetailsService.findUserByUsername(principal.getName()).get();
        Staffer staffer = user.getStaffer();
        Shop shop = staffer.getStafferShop();
        List<Request> requests = shop.getRequestsOfShop();
        List<Request> filterRequests;
        if (filter != null) {
            filterRequests = requestService.findRequestsByShop(filter);
        } else {
            filterRequests = requests;
        }
        model.addAttribute("filterRequests", filterRequests);
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Отображение страницы заявок магазина");
        return "requests/ownRequests";
    }
}
