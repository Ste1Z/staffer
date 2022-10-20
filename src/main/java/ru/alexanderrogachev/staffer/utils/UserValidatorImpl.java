package ru.alexanderrogachev.staffer.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alexanderrogachev.staffer.domain.User;
import ru.alexanderrogachev.staffer.services.UserDetailsServiceImpl;

import java.util.Optional;

@Component
public class UserValidatorImpl implements Validator {

    UserDetailsServiceImpl userDetailsService;

    @Autowired
    public UserValidatorImpl(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    //TODO переделать без ошибки в основе
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        try {
            userDetailsService.loadUserByUsername(user.getUsername());
        } catch (UsernameNotFoundException ignored) {
            return;
        }
        errors.rejectValue("username", "", "Пользователь с таким именем уже существует");
    }
}
