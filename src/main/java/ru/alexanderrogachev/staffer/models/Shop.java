package ru.alexanderrogachev.staffer.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Getter
@Setter
@Entity
@Table(name = "shops")
public class Shop {

    @Id
    @Column(name = "shop_name")
//    @NotEmpty(message = "Укажите название ММ")
    @Size(min = 2, max = 30, message = "Название не может быть короче 2 и длиннее 30 символов")
    private String name;

    @Column(name = "shop_code")
//    @NotEmpty(message = "Укажите код ММ")
    @Min(value = 0, message = "Код магазина не может быть отрицательным")
    @Size(max = 10, message = "Код магазина не может быть длиннее 10 символов")
    private int code;

    @Column(name = "shop_branch")
//    @NotEmpty(message = "Укажите филиал ММ")
    @Size(min = 5, max = 100, message = "Название не может быть короче 5 и длиннее 100 символов")
    private String branch;

    public Shop() {
    }

    public Shop(String name, int code, String branch) {
        this.name = name;
        this.code = code;
        this.branch = branch;
    }
}
