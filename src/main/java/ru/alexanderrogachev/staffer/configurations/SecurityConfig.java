package ru.alexanderrogachev.staffer.configurations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.alexanderrogachev.staffer.services.UserDetailsServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    //Настройка авторизации
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity//TODO включить защиту
                .csrf().disable()// TODO включить csrf
//                .authorizeRequests()
//                .antMatchers("/admin").hasRole("PRIME_ADMIN")
//                .antMatchers("/auth/login", "/auth/registration", "/process_login", "/error", "/branch/*).permitAll()
//                .anyRequest().hasAnyRole("STAFFER", "SHOP_ADMIN", "PRIME_ADMIN")//TODO поменять доступ ролей
////                .anyRequest().authenticated()//TODO отключить строку
//                .and()
                .formLogin()
                .loginPage("/auth/login").loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/requests", true)//TODO сделать нормальный редирект на главную
                .failureUrl("/auth/login?error")
                .and()
                .logout().logoutUrl("/auth/logout").logoutSuccessUrl("/auth/login");
    }

    //Настройка аутентификации
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(getPasswordEncoder());
    }

    //Шифрование паролей
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}