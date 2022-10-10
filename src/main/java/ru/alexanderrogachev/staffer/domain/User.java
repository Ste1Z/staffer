//package ru.alexanderrogachev.staffer.domain;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "credentials")
//public class User {
//    @Id
//    @JoinColumn
//    private int id;
//    @JoinColumn(name = "staffer_username")
//    private String username;
//    @JoinColumn(name = "staffer_password")
//    private String password;
//    @JoinColumn(name = "enabled")
//    private boolean enabled;
//    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "credentials", joinColumns = @JoinColumn(name = "role"))
//    @Enumerated(EnumType.STRING)
//    private Set<Role> roles;
//}
