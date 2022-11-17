package ru.alexanderrogachev.staffer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.alexanderrogachev.staffer.domain.User;
import ru.alexanderrogachev.staffer.models.Request;
import ru.alexanderrogachev.staffer.models.Shop;
import ru.alexanderrogachev.staffer.models.Staffer;
import ru.alexanderrogachev.staffer.services.RequestServiceImpl;
import ru.alexanderrogachev.staffer.services.ShopServiceImpl;
import ru.alexanderrogachev.staffer.services.StafferServiceImpl;
import ru.alexanderrogachev.staffer.services.UserDetailsServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class RequestController {

    private final RequestServiceImpl requestService;

    private final StafferServiceImpl stafferService;

    private final UserDetailsServiceImpl userDetailsService;

    private final ShopServiceImpl shopService;

    @Autowired
    public RequestController(RequestServiceImpl requestService, StafferServiceImpl stafferService, UserDetailsServiceImpl userDetailsService, ShopServiceImpl shopService) {
        this.requestService = requestService;
        this.stafferService = stafferService;
        this.userDetailsService = userDetailsService;
        this.shopService = shopService;
    }

    //________________________________________
    //Страница отображения заявок
    //________________________________________

    //Отображение списка запросов и фильтрация
    @GetMapping("/requests")
    public String requestsPage(@RequestParam(required = false) String filter, Model model) {
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

    //Добавление сотрудника в заявку по id
    @PostMapping("/requests/{requestId}")
    public String addStafferToRequest(HttpServletRequest http, @PathVariable("requestId") int requestId) {
        Principal principal = http.getUserPrincipal();
        User user = userDetailsService.findUserByUsername(principal.getName()).get();
        Staffer staffer = user.getStaffer();
        Request request = requestService.getRequest(requestId);
        List<Staffer> staffers = request.getNotApprovedStaffersList();
        staffers.add(staffer);
        request.setNotApprovedStaffersList(staffers);
        requestService.saveRequest(request);
        stafferService.saveStaffer(staffer);
        return "redirect:/requests";
    }

    //________________________________________
    //Страница создания заявок
    //________________________________________

    //Отображение страницы добавления заявки
    @GetMapping("/requests/add_request")
    public String addRequestPage(Model model, HttpServletRequest http) {
        Principal principal = http.getUserPrincipal();
        User user = userDetailsService.findUserByUsername(principal.getName()).get();
        Staffer staffer = user.getStaffer();
        String shopName = staffer.getHomeShopName();
        model.addAttribute("shopName", shopName);
        List<Integer> numberOfStaffers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        model.addAttribute("numberOfStaffers", numberOfStaffers);
        return "requests/add_request";
    }

    //Добавление новой заявки
    @PostMapping("/requests/add_request")
    public String addRequest(@ModelAttribute("request") @Valid Request request, HttpServletRequest http) {
        Principal principal = http.getUserPrincipal();
        User user = userDetailsService.findUserByUsername(principal.getName()).get();
        Staffer staffer = user.getStaffer();
        request.setShopName(staffer.getHomeShopName());
        requestService.autoSetDateOfRequest(request);
        requestService.saveRequest(request);
        return "redirect:/requests";
    }


    //____________________________________
    //Страница редактирования заявки по id
    //____________________________________

    //Отображение страницы с редактируемой заявкой
    @GetMapping("/requests/edit_request/{requestId}")
    public String editRequestPage(Model model, @PathVariable("requestId") int requestId) {
        Request request = requestService.getRequest(requestId);
        model.addAttribute("request", request);
        List<Integer> numberOfStaffers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        model.addAttribute("numberOfStaffers", numberOfStaffers);
        String reqPosition = request.getReqPosition();
        model.addAttribute("reqPosition", reqPosition);
        return "requests/edit_request";
    }

    //Сохранение новых данных заявки
    @PostMapping("/requests/edit_request/{requestId}")
    public String editRequest(@ModelAttribute("request") @Valid Request request, @PathVariable("requestId") int requestId) {
        Request oldVersionOfRequest = requestService.getRequest(requestId);
        request.setShopName(oldVersionOfRequest.getShopName());
        request.setDateOfRequest(oldVersionOfRequest.getDateOfRequest());
        request.setRequestId(requestId);
        if (request.getNumberOfReqStaffers() == 0) {
            request.setNumberOfReqStaffers(oldVersionOfRequest.getNumberOfReqStaffers());
        }
        if (request.getReqPosition() == null || request.getReqPosition().isEmpty()) {
            request.setReqPosition(oldVersionOfRequest.getReqPosition());
        }
        requestService.saveRequest(request);
        return "redirect:/requests";
    }

    //_______________________________________________
    //Страница со списком сотрудников из заявки по id
    //_______________________________________________

    //Отображение страницы со списком сотрудников
    @GetMapping("/requests/staffers_list/{requestId}")
    public String staffersListPage(Model model, @PathVariable("requestId") int requestId) {
        Request request = requestService.getRequest(requestId);
        List<Staffer> notApprovedStaffers = request.getNotApprovedStaffersList();
        model.addAttribute("notApprovedStaffers", notApprovedStaffers);
        List<Staffer> approvedStaffers = request.getApprovedStaffersList();
        model.addAttribute("approvedStaffers", approvedStaffers);
        return "requests/staffers_list";
    }

    //Одобрение выхода сотрудника
    @PostMapping("/requests/staffers_list/{requestId}/{stafferId}")
    public String staffersListApprove(@PathVariable("requestId") int requestId, @PathVariable("stafferId") int stafferId) {
        //Перемещение сотрудника из списка неодобренных сотрудников к одобренным и обратно в случае отсутствия его в оном
        Request request = requestService.getRequest(requestId);
        Staffer staffer = stafferService.findStafferById(stafferId);
        List<Staffer> notApprovedStaffers = request.getNotApprovedStaffersList();
        List<Staffer> approvedStaffers = request.getApprovedStaffersList();
        if (notApprovedStaffers.contains(staffer)) {
            notApprovedStaffers.remove(staffer);
            request.setNotApprovedStaffersList(notApprovedStaffers);
            approvedStaffers.add(staffer);
            request.setApprovedStaffersList(approvedStaffers);
        } else {
            approvedStaffers.remove(staffer);
            request.setApprovedStaffersList(approvedStaffers);
            notApprovedStaffers.add(staffer);
            request.setNotApprovedStaffersList(notApprovedStaffers);
        }
        requestService.saveRequest(request);
        stafferService.saveStaffer(staffer);
        return "redirect:/requests/staffers_list/{requestId}";
    }

    //_______________________________________________
    //Страница со списком сотрудников из заявки по id
    //_______________________________________________

    //Отображение страницы со списком заявок пользователя
    @GetMapping("/requests/my_requests")
    public String myRequestsPage(@RequestParam(required = false) String filter, Model model, HttpServletRequest http) {
        Principal principal = http.getUserPrincipal();
        User user = userDetailsService.findUserByUsername(principal.getName()).get();
        Staffer staffer = user.getStaffer();
        List<Request> requests = staffer.getNotApprovedStaffersRequests();
        List<Request> filterRequests;
        if (filter != null && !filter.isEmpty()) {
            filterRequests = requestService.findRequestByShopName(filter);
        } else {
            filterRequests = requests;
        }
        model.addAttribute("filterRequests", filterRequests);
        return "requests/my_requests";
    }

}
