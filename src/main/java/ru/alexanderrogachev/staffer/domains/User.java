package ru.alexanderrogachev.staffer.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.Getter;
import lombok.Setter;
import ru.alexanderrogachev.staffer.models.Staffer;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.Set;

//Класс пользователя

@Getter
@Setter
@Entity
@Table(name = "credentials")
@JsonIgnoreType
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_email")
    @Email(message = "Проверьте корректность почтового ящика (пример: example@mail.com)")
    @NotBlank(message = "Укажите почтовый ящик")
    @Size(min = 6, max = 50, message = "Почтовый ящик не может быть короче 6 и длиннее 50 символов")
    private String username;

    @Column(name = "user_password")
    @NotBlank(message = "Укажите пароль")
    @Size(min = 4, message = "Пароль не может быть короче 4 символов")
    private String password;

    @Transient
    @NotBlank(message = "Укажите пароль ещё раз")
    private String confirmPassword;

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

    //Выставляем роль в правах доступа для пользователя на основе выбранной должности
    public void setRoleByPosition(String position) {
        switch (position) {
            case "СВП":
            case "Продавец":
            case "Старший продавец":
                this.setUserRole(Collections.singleton(Role.ROLE_STAFFER));
                break;
            case "Товаровед":
            case "Заместитель директора":
            case "Директор":
                this.setUserRole(Collections.singleton(Role.ROLE_SHOP_ADMIN));
                break;
        }
    }

}
