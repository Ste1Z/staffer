package ru.alexanderrogachev.staffer.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ru.alexanderrogachev.staffer.domain.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "staffers")
public class Staffer {

    @Id
    @Column(name = "staffer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stafferId;

    @Column(name = "staffer_name")
//    @NotEmpty(message = "Укажите ваше имя")
    @Size(min = 2, max = 30, message = "Имя не может быть короче 2 и длиннее 30 символов")
    private String name;

    @Column(name = "staffer_surname")
//    @NotEmpty(message = "Укажите вашу фамилию")
    @Size(min = 2, max = 30, message = "Фамилия не может быть короче 2 и длиннее 30 символов")
    private String surname;

    @Column(name = "staffer_patronymic")
//    @NotEmpty(message = "Укажите ваше отчество")
    @Size(min = 2, max = 30, message = "Отчество не может быть короче 2 и длиннее 30 символов")
    private String patronymic;

    @Column(name = "staffer_date_of_birth")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @NotEmpty(message = "Укажите дату вашего рождения")
    private Date dateOfBirth;

    @Column(name = "home_shop_name")
//    @NotEmpty(message = "Укажите название вашего родного ММ")
    private String homeShopName;

    @Column(name = "shop_branch")
    private String branch;

    @Column(name = "staffer_position")
    private String position;

    @Column(name = "staffer_phone_number")
//    @NotEmpty(message = "Укажите номер вашего телефона")
    @Size(min = 10, max = 11, message = "Длина номера должна быть 10 или 11 символов")
    private String phoneNumber;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User usersStaffer;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "not_approved_staffers", joinColumns = @JoinColumn(name = "staffer_id"), inverseJoinColumns = @JoinColumn(name = "request_id"))
    private List<Request> notApprovedStaffersRequests;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "approved_staffers", joinColumns = @JoinColumn(name = "staffer_id"), inverseJoinColumns = @JoinColumn(name = "request_id"))
    private List<Request> approvedStaffersRequests;

    public Staffer() {
    }

    public Staffer(String name, String surname, String patronymic, Date dateOfBirth, String homeShopName, String branch, String position, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.homeShopName = homeShopName;
        this.branch = branch;
        this.position = position;
        this.phoneNumber = phoneNumber;
    }

}

