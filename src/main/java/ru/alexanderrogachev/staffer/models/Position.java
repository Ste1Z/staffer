package ru.alexanderrogachev.staffer.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "positions")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "positionId")
public class Position {

    @Id
    @Column(name = "position_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long positionId;

    @Column(name = "position_name")
    @NotBlank(message = "Укажите должность")
    @Size(min = 2, max = 30, message = "Имя не может быть короче 2 и длиннее 30 символов")
    private String positionName;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            mappedBy = "position")
    private List<Staffer> staffersOfPosition;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            mappedBy = "reqPosition")
    private List<Request> requestsOfPosition;

    public Position() {
    }

    public Position(String positionName) {
        this.positionName = positionName;
    }
}
