package ru.alexanderrogachev.staffer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alexanderrogachev.staffer.models.Staffer;
import ru.alexanderrogachev.staffer.repositories.StafferRepository;

import java.util.Date;
import java.util.List;

@Controller
public class StafferController {

    @Autowired
    private StafferRepository stafferRepository;

    //Отображение списка сотрудников и фильтрация
    @GetMapping("/staffers")
    public String staffers(@RequestParam(required = false) String filter, Model model) {
        List<Staffer> staffers = stafferRepository.findAll();
        List<Staffer> filterStaffers;
        if (filter != null && !filter.isEmpty()) {
            filterStaffers = stafferRepository.findByName(filter);
        } else {
            filterStaffers = staffers;
        }
        model.addAttribute("filterStaffers", filterStaffers);
        return "staffers";
    }

    //Добавление нового сотрудника
    @PostMapping("/staffers")
    public String add(@RequestParam String name, @RequestParam String surname, @RequestParam String patronymic,
                      @RequestParam Date dateOfBirth, @RequestParam String homeShop, Model model) {
        Staffer staffer = new Staffer(name, surname, patronymic, dateOfBirth, homeShop);
        stafferRepository.save(staffer);
        Iterable<Staffer> staffers = stafferRepository.findAll();
        model.addAttribute("staffers", staffers);
        return "staffers";
    }

}
