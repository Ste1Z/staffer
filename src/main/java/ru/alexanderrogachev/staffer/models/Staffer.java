package ru.alexanderrogachev.staffer.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "staffers")
public class Staffer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int stafferId;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Column(name = "home_shop")
    private String homeShop;
    @Column(name = "email")
    private String email;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "staffer_requests", joinColumns = @JoinColumn(name = "staffer_id"), inverseJoinColumns = @JoinColumn(name = "request_id"))
    private List<Request> stafferRequests;

    public Staffer() {
    }

    public Staffer(String name, String surname, String patronymic, Date dateOfBirth, String homeShop, String email) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.homeShop = homeShop;
        this.email = email;
    }

    public void addRequestToStaffer(Request request) {
        if (stafferRequests == null) {
            stafferRequests = new ArrayList<>();
        }
        stafferRequests.add(request);
    }

    @Override
    public String toString() {
        return "Staffer{" +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", homeShop='" + homeShop + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
