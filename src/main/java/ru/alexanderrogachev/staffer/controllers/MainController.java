package ru.alexanderrogachev.staffer.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.alexanderrogachev.staffer.models.Request;
import ru.alexanderrogachev.staffer.models.Staffer;
import ru.alexanderrogachev.staffer.services.RequestServiceImpl;
import ru.alexanderrogachev.staffer.services.UserDetailsServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {

    private static final Logger logger = LogManager.getLogger(MainController.class);

    private final UserDetailsServiceImpl userDetailsService;
    private final RequestServiceImpl requestService;

    public MainController(UserDetailsServiceImpl userDetailsService, RequestServiceImpl requestService) {
        this.userDetailsService = userDetailsService;
        this.requestService = requestService;
    }

    @GetMapping("/")
    public String main(HttpServletRequest http, Model model) {
        Staffer currentStaffer = userDetailsService.getStafferFromLoggedUser(http);
        List<Request> currentStafferRequestsWithoutApprove = requestService.getAllRequests().stream().filter(r -> r.getNotApprovedStaffersList().contains(currentStaffer)).collect(Collectors.toList());
        List<Request> currentStafferRequestsWithApprove = requestService.getAllRequests().stream().filter(r -> r.getApprovedStaffersList().contains(currentStaffer)).collect(Collectors.toList());
        model.addAttribute("currentStafferRequestsWithoutApprove", currentStafferRequestsWithoutApprove);
        model.addAttribute("currentStafferRequestsWithApprove", currentStafferRequestsWithApprove);
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Отображение главной страницы");
        return "main";
    }

}
