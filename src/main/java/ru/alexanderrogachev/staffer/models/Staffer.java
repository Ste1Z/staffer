package ru.alexanderrogachev.staffer.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ru.alexanderrogachev.staffer.domain.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "staffers")
public class Staffer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stafferId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @Column(name = "home_shop")
    private String homeShop;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User usersStaffer;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "staffer_requests", joinColumns = @JoinColumn(name = "staffer_id"), inverseJoinColumns = @JoinColumn(name = "request_id"))
    private List<Request> stafferRequests;

    public Staffer() {
    }

    public Staffer(String name, String surname, String patronymic, Date dateOfBirth, String homeShop, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.homeShop = homeShop;
        this.phoneNumber = phoneNumber;
    }
}
