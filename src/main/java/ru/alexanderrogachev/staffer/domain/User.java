package ru.alexanderrogachev.staffer.domain;

import lombok.Getter;
import lombok.Setter;
import ru.alexanderrogachev.staffer.models.Staffer;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

//Класс пользователя

@Getter
@Setter
@Entity
@Table(name = "credentials")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_username")
    @NotEmpty(message = "Укажите логин")
    @Size(min = 2, max = 30, message = "Имя должно быть длиной не менее 2 и не более 30 символов")
    private String username;

    @Column(name = "user_password")
    @NotEmpty(message = "Укажите пароль")
    @Size(min = 2, message = "Имя должно быть длиной не менее 2 символов")
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> userRole;

    @Column(name = "enabled")
    private boolean enabled;

    @OneToOne(mappedBy = "usersStaffer", cascade = CascadeType.ALL)
    private Staffer staffer;

    public User() {
    }

    public User(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }
}
