package ru.alexanderrogachev.staffer.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

//Класс пользователя

@Getter
@Setter
@Entity
@Table(name = "credentials")
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "staffer_username")
    private String username;
    @Column(name = "staffer_password")
    private String password;
    @Column(name = "enabled")
    private boolean enabled;
//    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "credentials", joinColumns = @JoinColumn(name = "roles"))
//    @Enumerated(EnumType.STRING)
//    private Set<Role> roles;

    public User() {
    }

    public User(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
//        this.roles = roles;
    }
}
