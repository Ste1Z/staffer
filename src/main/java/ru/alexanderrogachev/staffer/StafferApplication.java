package ru.alexanderrogachev.staffer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class StafferApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(StafferApplication.class, args);
    }


}
