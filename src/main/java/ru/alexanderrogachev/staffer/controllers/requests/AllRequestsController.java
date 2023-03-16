package ru.alexanderrogachev.staffer.controllers.requests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.alexanderrogachev.staffer.domains.User;
import ru.alexanderrogachev.staffer.models.Request;
import ru.alexanderrogachev.staffer.models.Shop;
import ru.alexanderrogachev.staffer.models.Staffer;
import ru.alexanderrogachev.staffer.services.RequestServiceImpl;
import ru.alexanderrogachev.staffer.services.StafferServiceImpl;
import ru.alexanderrogachev.staffer.services.UserDetailsServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;


//Страница отображения заявок
@Controller
@RequestMapping("/requests")
public class AllRequestsController {

    private static final Logger logger = LogManager.getLogger(AllRequestsController.class);

    private final RequestServiceImpl requestService;
    private final StafferServiceImpl stafferService;
    private final UserDetailsServiceImpl userDetailsService;


    @Autowired
    public AllRequestsController(RequestServiceImpl requestService, StafferServiceImpl stafferService, UserDetailsServiceImpl userDetailsService) {
        this.requestService = requestService;
        this.stafferService = stafferService;
        this.userDetailsService = userDetailsService;
    }


    //Отображение списка запросов и фильтрация
    @GetMapping("/allRequests")
    public String requestsPage(@RequestParam(required = false) Shop filter, Model model) {
        List<Request> requests = requestService.getAllRequests();
        List<Request> filterRequests;
        if (filter != null) {
            filterRequests = requestService.findRequestsByShop(filter);
        } else {
            filterRequests = requests;
        }
        model.addAttribute("filterRequests", filterRequests);
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Отображение страницы со всеми заявками");
        return "requests/allRequests";
    }

    //Добавление сотрудника в заявку по id
    @PostMapping("allRequests/{requestId}")
    public String addStafferToRequest(HttpServletRequest http, @PathVariable("requestId") Long requestId) {
        Staffer staffer = userDetailsService.getStafferFromLoggedUser(http);
        Request request = requestService.getRequest(requestId);
        List<Staffer> staffers = request.getNotApprovedStaffersList();
        staffers.add(staffer);
        request.setNotApprovedStaffersList(staffers);
        requestService.saveRequest(request);
        stafferService.saveStaffer(staffer);
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Сотрудник добавлен в заявку");
        return "redirect:/requests/allRequests";
    }


}
