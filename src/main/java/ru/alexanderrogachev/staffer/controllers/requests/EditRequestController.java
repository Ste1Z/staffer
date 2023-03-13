package ru.alexanderrogachev.staffer.controllers.requests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.alexanderrogachev.staffer.models.Position;
import ru.alexanderrogachev.staffer.models.Request;
import ru.alexanderrogachev.staffer.services.RequestServiceImpl;

import javax.validation.Valid;
import java.util.List;

//Страница редактирования заявки по id
@Controller
@RequestMapping("/requests")
public class EditRequestController {

    private static final Logger logger = LogManager.getLogger(EditRequestController.class);

    private final RequestServiceImpl requestService;

    @Autowired
    public EditRequestController(RequestServiceImpl requestService) {
        this.requestService = requestService;
    }

    //Отображение страницы с редактируемой заявкой
    @GetMapping("/editRequest/{requestId}")
    public String editRequestPage(Model model, @PathVariable("requestId") Long requestId) {
        Request request = requestService.getRequest(requestId);
        model.addAttribute("request", request);
        Position reqPosition = request.getRequestReqPosition();
        model.addAttribute("reqPosition", reqPosition);
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Отображение страницы изменения заявки");
        return "requests/editRequest";
    }

    //Сохранение новых данных заявки
    @PostMapping("/editRequest/{requestId}")
    public String editRequest(@ModelAttribute("request") @Valid Request request, @PathVariable("requestId") Long requestId) {
        Request oldVersionOfRequest = requestService.getRequest(requestId);
        request.setRequestShop(oldVersionOfRequest.getRequestShop());
        request.setRequestCreationDate(oldVersionOfRequest.getRequestCreationDate());
        request.setRequestId(requestId);
        if (request.getRequestNumOfReqStaffers() == 0) {
            request.setRequestNumOfReqStaffers(oldVersionOfRequest.getRequestNumOfReqStaffers());
        }
        if (request.getRequestReqPosition() == null) {
            request.setRequestReqPosition(oldVersionOfRequest.getRequestReqPosition());
        }
        requestService.saveRequest(request);
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Изменения сохранены");
        return "redirect:/requests/allRequests";
    }
}
