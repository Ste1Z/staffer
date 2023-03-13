package ru.alexanderrogachev.staffer.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alexanderrogachev.staffer.domains.User;
import ru.alexanderrogachev.staffer.models.Staffer;
import ru.alexanderrogachev.staffer.services.UserDetailsServiceImpl;

@Component
public class UserValidatorImpl implements Validator {

    private static final Logger logger = LogManager.getLogger(UserValidatorImpl.class);

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
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Начало валидации пользователя");

        User user = (User) target;
        //Проверка наличия пользователя с таким логином в БД
        if (userDetailsService.findUserByUsername(user.getUsername()).isPresent()) {
            logger.info("[" + this.getClass().getSimpleName() + "]" + " Данный почтовый ящик уже используется");
            errors.rejectValue("username", "", "Данный почтовый ящик уже используется");
        }
        //Проверка на соответствие введенных паролей
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            logger.info("[" + this.getClass().getSimpleName() + "]" + " Введенные пароли не совпадают");
            errors.rejectValue("passwordConfirm", "", "Введенные пароли не совпадают");
        }
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Валидация пройдена");
    }
}
