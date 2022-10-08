package ru.alexanderrogachev.staffer.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "shops")
public class Shop {
    @Id
    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private int code;

    public Shop() {
    }

    public Shop(String name, int code) {
        this.name = name;
        this.code = code;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "name='" + name + '\'' +
                ", code=" + code +
                '}';
    }
}
