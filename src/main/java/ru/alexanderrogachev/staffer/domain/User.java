package ru.alexanderrogachev.staffer.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

//Класс пользователя

@Getter
@Setter
@Entity
@Table(name = "credentials")
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 30, message = "Имя должно быть длиной не менее 2 и не более 30 символов")
    @Column(name = "staffer_username")
    private String username;

    @NotEmpty(message = "Пароль не должен быть пустым")
    @Size(min = 2, message = "Имя должно быть длиной не менее 2 символов")
    @Column(name = "staffer_password")
    private String password;

    @Column(name = "roles")
    private String role;

    @Column(name = "enabled")
    private boolean enabled;

    public User() {
    }

    public User(String username, String password, boolean enabled, String role) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
    }
}
