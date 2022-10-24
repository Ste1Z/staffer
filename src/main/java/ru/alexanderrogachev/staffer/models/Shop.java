package ru.alexanderrogachev.staffer.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//TODO добавить город

@Getter
@Setter
@Entity
@Table(name = "shops")
public class Shop {

    @Id
    @Column(name = "shop_name")
    private String name;

    @Column(name = "shop_code")
    private int code;

    @Column(name = "shop_branch")
    private String branch;

    public Shop() {
    }

    public Shop(String name, int code, String branch) {
        this.name = name;
        this.code = code;
        this.branch = branch;
    }
}
