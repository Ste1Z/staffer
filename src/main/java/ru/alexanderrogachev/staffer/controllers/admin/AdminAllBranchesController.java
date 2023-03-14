package ru.alexanderrogachev.staffer.controllers.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alexanderrogachev.staffer.services.BranchServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminAllBranchesController {

    private static final Logger logger = LogManager.getLogger(AdminAllBranchesController.class);

    private final BranchServiceImpl branchService;

    public AdminAllBranchesController(BranchServiceImpl branchService) {
        this.branchService = branchService;
    }

    @GetMapping("/allBranches")
    public String adminAllBranchesPage(Model model) {
        model.addAttribute("allBranches", branchService.getAllBranches());
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Отображение страницы админки со всеми филиалами");
        return "admin/allBranches";
    }

}
