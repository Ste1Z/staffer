package ru.alexanderrogachev.staffer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    //________________________________________
    //Страница добавления и отображения заявок
    //________________________________________

    //Отображение списка запросов и фильтрация
    @GetMapping("/requests/add_request")
    public String requests(@RequestParam(required = false) String filter, Model model) {
        List<Request> requests = requestService.getAllRequests();
        List<Request> filterRequests;
        if (filter != null && !filter.isEmpty()) {
            filterRequests = requestService.findRequestByShopName(filter);
        } else {
            filterRequests = requests;
        }
        model.addAttribute("filterRequests", filterRequests);
        return "requests/add_request";
    }

    //Добавление новой заявки
    @PostMapping("/requests/add_request")
    public String addRequest(@ModelAttribute("request") Request request) {
        requestService.autoSetDateOfRequest(request);
        requestService.saveRequest(request);
        return "requests/add_request";
    }

    //____________________________________
    //Страница редактирования заявки по id
    //____________________________________

    //Отображение страницы с редактируемой заявкой
    @GetMapping("/requests/edit_request/{requestId}")
    public String editRequestPage(Model model, @PathVariable("requestId") int requestId) {
        Request request = requestService.getRequest(requestId);
        model.addAttribute("request", request);
        return "requests/edit_request";
    }

    //Сохранение новых данных заявки
    @PostMapping("/requests/edit_request/{requestId}")
    public String editRequest(@ModelAttribute("request") Request request, @PathVariable("requestId") int requestId) {
        Request oldVersionOfRequest = requestService.getRequest(requestId);
        request.setDateOfRequest(oldVersionOfRequest.getDateOfRequest());
        request.setRequestId(requestId);
        requestService.saveRequest(request);
        return "redirect:/requests/add_request";
    }

    //_______________________________________________
    //Страница со списком сотрудников из заявки по id
    //_______________________________________________

    //Отображение страницы со списком сотрудников
    @GetMapping("/requests/staffers_list/{requestId}")
    public String staffersListPage(@ModelAttribute("request") Request request, @PathVariable("requestId") int requestId){

        return "requests/staffers_list";
    }

}
