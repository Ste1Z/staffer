package ru.alexanderrogachev.staffer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alexanderrogachev.staffer.models.Request;
import ru.alexanderrogachev.staffer.services.RequestServiceImpl;

import java.util.List;

@Controller
public class RequestController {

    private final RequestServiceImpl requestService;

    @Autowired
    public RequestController(RequestServiceImpl requestService) {
        this.requestService = requestService;
    }

    //Отображение списка запросов и фильтрация
    @GetMapping("/requests")
    public String requests(@RequestParam(required = false) String filter, Model model) {
        List<Request> requests = requestService.getAllRequests();
        List<Request> filterRequests;
        if (filter != null && !filter.isEmpty()) {
            filterRequests = requestService.findRequestByShopName(filter);
        } else {
            filterRequests = requests;
        }
        model.addAttribute("filterRequests", filterRequests);
        return "requests";
    }

    //Добавление нового запроса
    @PostMapping("/requests")
    public String add(@ModelAttribute("request") Request request) {
        requestService.autoSetDateOfRequest(request);
        requestService.saveRequest(request);
        return "requests";
    }
}
