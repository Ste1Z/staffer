package ru.alexanderrogachev.staffer.controllers.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alexanderrogachev.staffer.models.Branch;
import ru.alexanderrogachev.staffer.services.BranchServiceImpl;
import ru.alexanderrogachev.staffer.utils.BranchValidatorImpl;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminCreateBranchController {

    private static final Logger logger = LogManager.getLogger(AdminCreateBranchController.class);

    private final BranchServiceImpl branchService;
    private final BranchValidatorImpl branchValidator;

    @Autowired
    public AdminCreateBranchController(BranchServiceImpl branchService, BranchValidatorImpl branchValidator) {
        this.branchService = branchService;
        this.branchValidator = branchValidator;
    }

    @GetMapping("/createBranch")
    public String adminCreateBranchPage(@ModelAttribute("branch") Branch branch) {
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Отображение страницы админки со созданием филиала");
        return "admin/createBranch";
    }

    @PostMapping("/createBranch")
    public String createBranch(@Valid Branch branch, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "admin/createBranch";
        branchValidator.validate(branch, bindingResult);
        branchService.saveBranch(branch);
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Филиал успешно создан");
        return "redirect:/admin/allBranches";
    }
}
