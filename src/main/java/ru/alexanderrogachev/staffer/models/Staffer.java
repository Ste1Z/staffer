package ru.alexanderrogachev.staffer.models;

import java.util.Date;

public class Staffer {

    private int stafferId;

    private String name;

    private String surname;

    private String patronymic;

    private Date dateOfBirth;

    private String homeShop;

    private String email;

    public Staffer(int stafferId, String name, String surname, String patronymic, Date dateOfBirth, String homeShop, String email) {
        this.stafferId = stafferId;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.homeShop = homeShop;
        this.email = email;
    }

    public int getStafferId() {
        return stafferId;
    }

    public void setStafferId(int stafferId) {
        this.stafferId = stafferId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getHomeShop() {
        return homeShop;
    }

    public void setHomeShop(String homeShop) {
        this.homeShop = homeShop;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Staffer{" +
                "stafferId=" + stafferId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", homeShop='" + homeShop + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
