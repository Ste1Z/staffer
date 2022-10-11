package ru.alexanderrogachev.staffer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alexanderrogachev.staffer.models.Request;
import ru.alexanderrogachev.staffer.repositories.RequestRepository;

import java.util.Date;
import java.util.List;

@Controller
public class RequestController {

    @Autowired
    private RequestRepository requestRepository;

    @GetMapping("/requests")
    public String requests(@RequestParam(required = false) String filter, Model model) {
        List<Request> requests = requestRepository.findAll();
        List<Request> filterRequests;
        if (filter != null && !filter.isEmpty()) {
            filterRequests = requestRepository.findByShopName(filter);
        } else {
            filterRequests = requests;
        }
        model.addAttribute("filterRequests", filterRequests);
        return "requests";
    }

    @PostMapping("/requests")
    public String add(@RequestParam int stafferId, @RequestParam String shopName, @RequestParam Date dateOfRequest, @RequestParam Date dateOfWork,
                      @RequestParam Date startTime, @RequestParam Date endTime, @RequestParam Boolean confirmation, Model model) {
        Request request = new Request(stafferId, shopName, dateOfRequest, dateOfWork, startTime, endTime, confirmation);
        requestRepository.save(request);
        Iterable<Request> requests = requestRepository.findAll();
        model.addAttribute("requests", requests);
        return "requests";
    }
}
