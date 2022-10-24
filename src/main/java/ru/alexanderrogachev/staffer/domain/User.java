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
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 30, message = "Имя должно быть длиной не менее 2 и не более 30 символов")
    @Column(name = "user_username")
    private String username;

    @NotEmpty(message = "Пароль не должен быть пустым")
    @Size(min = 2, message = "Имя должно быть длиной не менее 2 символов")
    @Column(name = "user_password")
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
