package ru.alexanderrogachev.staffer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alexanderrogachev.staffer.models.Staffer;
import ru.alexanderrogachev.staffer.services.StafferServiceImpl;

import java.util.List;

@Controller
public class StafferController {

    private final StafferServiceImpl stafferService;

    @Autowired
    public StafferController(StafferServiceImpl stafferService) {
        this.stafferService = stafferService;
    }

    //Отображение списка сотрудников и фильтрация
    @GetMapping("/staffers")
    public String staffers(@RequestParam(required = false) String filter, Model model) {
        List<Staffer> staffers = stafferService.getAllStaffers();
        List<Staffer> filterStaffers;
        if (filter != null && !filter.isEmpty()) {
            filterStaffers = stafferService.findStafferByName(filter);
        } else {
            filterStaffers = staffers;
        }
        model.addAttribute("filterStaffers", filterStaffers);
        return "staffers";
    }

    //Добавление нового сотрудника
    @PostMapping("/staffers")
    public String add(@ModelAttribute("staffer") Staffer staffer) {
        stafferService.saveStaffer(staffer);
        return "staffers";
    }



}
