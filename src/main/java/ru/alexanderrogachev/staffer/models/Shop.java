package ru.alexanderrogachev.staffer.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;


@Getter
@Setter
@Entity
@Table(name = "shops")
public class Shop {

    @Id
    @Column(name = "shop_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "shop_name")
//    @NotEmpty(message = "Укажите название ММ")
    @Size(min = 2, max = 30, message = "Название не может быть короче 2 и длиннее 30 символов")
    private String name;

    @Column(name = "shop_code")
//    @NotEmpty(message = "Укажите код ММ")
    @Min(value = 0, message = "Код магазина не может быть отрицательным")
    @Size(max = 10, message = "Код магазина не может быть длиннее 10 символов")
    private int code;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "shop_branch_id")
    private Branch branch;

    public Shop() {
    }

    public Shop(String name, int code) {
        this.name = name;
        this.code = code;
    }
}
