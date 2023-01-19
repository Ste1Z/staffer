package ru.alexanderrogachev.staffer.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "branches")
public class Branch {

    @Id
    @Column(name = "branch_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "branch_name")
    private String branch;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "shop")
    private List<Shop> shopsOfBranch;

    public Branch() {
    }

    public Branch(String branch) {
        this.branch = branch;
    }
}
