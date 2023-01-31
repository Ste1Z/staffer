package ru.alexanderrogachev.staffer.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.BindingResult;
import ru.alexanderrogachev.staffer.domains.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "staffers")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "stafferId")
public class Staffer {

    @Id
    @Column(name = "staffer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long stafferId;

    @Column(name = "staffer_name")
    @NotBlank(message = "Укажите ваше имя")
    @Size(min = 2, max = 30, message = "Имя не может быть короче 2 и длиннее 30 символов")
    private String name;

    @Column(name = "staffer_surname")
    @NotBlank(message = "Укажите вашу фамилию")
    @Size(min = 2, max = 30, message = "Фамилия не может быть короче 2 и длиннее 30 символов")
    private String surname;

    @Column(name = "staffer_patronymic")
    @NotBlank(message = "Укажите ваше отчество")
    @Size(min = 2, max = 30, message = "Отчество не может быть короче 2 и длиннее 30 символов")
    private String patronymic;

    @Column(name = "staffer_date_of_birth")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Дата рождения не может быть позднее текущего дня")
    @NotNull(message = "Укажите дату вашего рождения")
    private Date dateOfBirth;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "staffer_branch_id")
    @NotNull(message = "Укажите ваш филиал")
    private Branch branch;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "home_shop_id")
    @NotNull(message = "Укажите ваш магазин")
    private Shop homeShop;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "staffer_position_id")
    @NotNull(message = "Укажите ваш филиал")
    private Position position;

    @Column(name = "staffer_phone_number")
    @NotBlank(message = "Укажите номер вашего телефона")
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

    public Staffer(String name, String surname, String patronymic, Date dateOfBirth, Branch branch, Shop homeShop, Position position, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.branch = branch;
        this.homeShop = homeShop;
        this.position = position;
        this.phoneNumber = phoneNumber;
    }

    //Получаем фамилию И. О.
    public String getFullName() {
        return this.surname + " " + this.name.charAt(0) + ". " + this.patronymic.charAt(0) + ".";
    }

    //Парсим дату из строки и устанавливаем пользователю
    public void setParsedDateOfBirth(String staffersDateOfBirth, BindingResult bindingResult) {
        try {
            Date dateOfBirth = new SimpleDateFormat("dd.MM.yyyy").parse(staffersDateOfBirth);
            this.setDateOfBirth(dateOfBirth);
        } catch (ParseException e) {
            bindingResult.rejectValue("dateOfBirth", "", "Выберите дату рождения");
        }
    }

}

