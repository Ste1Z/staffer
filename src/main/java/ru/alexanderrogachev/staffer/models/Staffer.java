package ru.alexanderrogachev.staffer.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ru.alexanderrogachev.staffer.domains.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "staffers")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "stafferId")
public class Staffer {

    @Id
    @Column(name = "staffer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stafferId;

    @Column(name = "staffer_name")
    @Size(min = 2, max = 30, message = "Имя не может быть короче 2 и длиннее 30 символов")
    @NotBlank(message = "Укажите ваше имя")
    private String stafferName;

    @Column(name = "staffer_surname")
    @Size(min = 2, max = 30, message = "Фамилия не может быть короче 2 и длиннее 30 символов")
    @NotBlank(message = "Укажите вашу фамилию")
    private String stafferSurname;

    @Column(name = "staffer_patronymic")
    @Size(min = 2, max = 30, message = "Отчество не может быть короче 2 и длиннее 30 символов")
    @NotBlank(message = "Укажите ваше отчество")
    private String stafferPatronymic;

    @Column(name = "staffer_date_of_birth")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Дата рождения не может быть позднее текущего дня")
    @NotNull(message = "Укажите дату вашего рождения")
    private Date stafferDateOfBirth;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "staffer_branch_id")
    @NotNull(message = "Укажите ваш филиал")
    private Branch stafferBranch;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "home_shop_id")
    @NotNull(message = "Укажите ваш магазин")
    private Shop stafferShop;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "staffer_position_id")
    @NotNull(message = "Укажите ваш филиал")
    private Position stafferPosition;

    @Column(name = "staffer_phone_number")
    @NotBlank(message = "Укажите номер вашего телефона")
    private String stafferPhoneNumber;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User usersStaffer;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "not_approved_staffers", joinColumns = @JoinColumn(name = "staffer_id"), inverseJoinColumns = @JoinColumn(name = "request_id"))
    private List<Request> notApprovedStafferRequests;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "approved_staffers", joinColumns = @JoinColumn(name = "staffer_id"), inverseJoinColumns = @JoinColumn(name = "request_id"))
    private List<Request> approvedStafferRequests;

    //Получаем фамилию И. О.
    public String getFullName() {
        return this.stafferSurname + " " + this.stafferName.charAt(0) + ". " + this.stafferPatronymic.charAt(0) + ".";
    }

}

