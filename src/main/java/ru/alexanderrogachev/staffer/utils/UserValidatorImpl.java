package ru.alexanderrogachev.staffer.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alexanderrogachev.staffer.domains.User;
import ru.alexanderrogachev.staffer.services.UserDetailsServiceImpl;

@Component
public class UserValidatorImpl implements Validator {

    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public UserValidatorImpl(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (userDetailsService.findUserByEmail(user.getEmail()).isPresent()) {
            errors.rejectValue("username", "", "Такой пользователь уже зарегистрирован");
        }
    }
}
