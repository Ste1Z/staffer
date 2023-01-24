package ru.alexanderrogachev.staffer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.alexanderrogachev.staffer.models.Branch;
import ru.alexanderrogachev.staffer.services.BranchServiceImpl;

@Controller
public class BranchController {

    private final BranchServiceImpl branchService;

    @Autowired
    public BranchController(BranchServiceImpl branchService) {
        this.branchService = branchService;
    }

    //Запрос филиала по id
    @GetMapping("/branch/{id}")
    @ResponseBody
    public Branch getBranch(@PathVariable Long id) {
        return branchService.getBranch(id);
    }
}
