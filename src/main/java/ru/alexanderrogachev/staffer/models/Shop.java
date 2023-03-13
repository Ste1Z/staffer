package ru.alexanderrogachev.staffer.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "shops")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "shopId")
public class Shop {

    @Id
    @Column(name = "shop_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shopId;

    @Column(name = "shop_name")
    @Size(min = 2, max = 30, message = "Название не может быть короче 2 и длиннее 30 символов")
    @NotBlank(message = "Укажите название магазина")
    private String shopName;

    @Column(name = "shop_code")
    @Min(value = 0, message = "Код магазина не может быть отрицательным")
    @NotNull(message = "Укажите код магазина")
    private Integer shopCode;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_branch_id")
    @NotNull(message = "Укажите филиал магазина")
    private Branch shopBranch;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            mappedBy = "stafferShop", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Staffer> staffersOfShop;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            mappedBy = "requestShop", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Request> requestsOfShop;

}
