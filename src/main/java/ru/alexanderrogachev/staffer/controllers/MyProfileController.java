package ru.alexanderrogachev.staffer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.alexanderrogachev.staffer.domain.User;
import ru.alexanderrogachev.staffer.models.Staffer;
import ru.alexanderrogachev.staffer.services.StafferServiceImpl;
import ru.alexanderrogachev.staffer.services.UserDetailsServiceImpl;

@Controller
public class MyProfileController {

    private final UserDetailsServiceImpl userDetailsService;

    private final StafferServiceImpl stafferService;

    @Autowired
    public MyProfileController(UserDetailsServiceImpl userDetailsService, StafferServiceImpl stafferService) {
        this.userDetailsService = userDetailsService;
        this.stafferService = stafferService;
    }

    @GetMapping("/my_profile")
    public String myProfilePage(Model model) {

        return "my_profile";
    }
}
