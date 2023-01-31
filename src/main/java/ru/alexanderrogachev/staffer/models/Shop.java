package ru.alexanderrogachev.staffer.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "shops")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Shop {

    @Id
    @Column(name = "shop_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "shop_name")
    @NotBlank(message = "Укажите название магазина")
    @Size(min = 2, max = 30, message = "Название не может быть короче 2 и длиннее 30 символов")
    private String name;

    @Column(name = "shop_code")
    @NotBlank(message = "Укажите код магазина")
    @Min(value = 0, message = "Код магазина не может быть отрицательным")
    @Size(max = 10, message = "Код магазина не может быть длиннее 10 символов")
    private int code;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "shop_branch_id")
    @NotNull(message = "Укажите филиал магазина")
    private Branch branch;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            mappedBy = "homeShop")
    private List<Staffer> staffersOfShop;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            mappedBy = "shopName")
    private List<Request> requestsOfShop;

    public Shop() {
    }

    public Shop(String name, int code, Branch branch) {
        this.name = name;
        this.code = code;
        this.branch = branch;
    }
}
