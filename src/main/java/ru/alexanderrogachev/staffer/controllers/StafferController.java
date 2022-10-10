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

@Controller
public class StafferController {

    @Autowired
    private StafferRepository stafferRepository;

    @GetMapping("/staffers")
    public String staffers(@RequestParam(required = false) String filter, Model model) {
        Iterable<Staffer> staffers = stafferRepository.findAll();
        if (filter != null && !filter.isEmpty()) {
            staffers = stafferRepository.findByName(filter);
        } else {
            staffers = stafferRepository.findAll();
        }
        model.addAttribute("staffers", staffers);
        model.addAttribute("filter", staffers);
        return "staffers";
    }

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
