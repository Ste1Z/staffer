package ru.alexanderrogachev.staffer.controllers.requests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alexanderrogachev.staffer.models.Request;
import ru.alexanderrogachev.staffer.models.Staffer;
import ru.alexanderrogachev.staffer.services.RequestServiceImpl;
import ru.alexanderrogachev.staffer.services.StafferServiceImpl;

import java.util.List;

//Страница со списком сотрудников из заявки по id
@Controller
@RequestMapping("/requests")
public class StaffersListController {

    private static final Logger logger = LogManager.getLogger(StaffersListController.class);

    private final RequestServiceImpl requestService;
    private final StafferServiceImpl stafferService;

    @Autowired
    public StaffersListController(RequestServiceImpl requestService, StafferServiceImpl stafferService) {
        this.requestService = requestService;
        this.stafferService = stafferService;
    }

    //Отображение страницы со списком сотрудников
    @GetMapping("/staffersList/{requestId}")
    public String staffersListPage(Model model, @PathVariable("requestId") Long requestId) {
        Request request = requestService.getRequest(requestId);
        List<Staffer> notApprovedStaffers = request.getNotApprovedStaffersList();
        model.addAttribute("notApprovedStaffers", notApprovedStaffers);
        List<Staffer> approvedStaffers = request.getApprovedStaffersList();
        model.addAttribute("approvedStaffers", approvedStaffers);
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Отображение страницы сотрудников в заявке");
        return "requests/staffersList";
    }

    //Перемещение сотрудника из списка неодобренных сотрудников к одобренным и обратно в случае отсутствия его в оном
    @PostMapping("/staffersList/{requestId}/{stafferId}")
    public String staffersListApprove(@PathVariable("requestId") Long requestId, @PathVariable("stafferId") Long stafferId) {
        Request request = requestService.getRequest(requestId);
        Staffer staffer = stafferService.findStafferById(stafferId).get();
        request.switchStafferInRequest(staffer);
        requestService.saveRequest(request);
        stafferService.saveStaffer(staffer);
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Перемещение сотрудника между списками одобренных/не одобренных");
        return "redirect:/requests/staffersList/{requestId}";
    }
}
