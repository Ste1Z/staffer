package ru.alexanderrogachev.staffer.controllers.requests;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alexanderrogachev.staffer.models.Request;
import ru.alexanderrogachev.staffer.services.PositionServiceImpl;
import ru.alexanderrogachev.staffer.services.RequestServiceImpl;

import javax.validation.Valid;


//Страница редактирования заявки по id
@Controller
@RequestMapping("/requests")
public class EditRequestController {

    private static final Logger logger = LogManager.getLogger(EditRequestController.class);

    private final RequestServiceImpl requestService;
    private final PositionServiceImpl positionService;

    @Autowired
    public EditRequestController(RequestServiceImpl requestService, PositionServiceImpl positionService) {
        this.requestService = requestService;
        this.positionService = positionService;
    }

    //Отображение страницы с редактируемой заявкой
    @GetMapping("/editRequest/{requestId}")
    public String editRequestPage(Model model, @PathVariable("requestId") Long requestId) {
        model.addAttribute("currentRequest", requestService.getRequest(requestId));
        model.addAttribute("allPositions", positionService.getAllPositions());
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Отображение страницы изменения заявки");
        return "requests/editRequest";
    }

    //Сохранение новых данных заявки
    @PostMapping("/editRequest/{requestId}")
    public String editRequest(@Valid Request request, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("allPositions", positionService.getAllPositions());
            return "requests/editRequest";
        }
        requestService.saveRequest(request);
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Изменения сохранены");
        return "redirect:/requests/ownRequests";
    }
}
