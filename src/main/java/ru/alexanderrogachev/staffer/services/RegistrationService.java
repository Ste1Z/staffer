package ru.alexanderrogachev.staffer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alexanderrogachev.staffer.domain.Role;
import ru.alexanderrogachev.staffer.domain.User;
import ru.alexanderrogachev.staffer.repositories.UserRepository;

import java.util.Collections;

@Service
public class RegistrationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        //TODO поменять установку ролей
        user.setUserRole(Collections.singleton(Role.ROLE_STAFFER));
        user.setEnabled(true);
        userRepository.save(user);
    }
}
