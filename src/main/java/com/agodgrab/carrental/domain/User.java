package com.kodilla.kodilla.diplomaBackend.domain;

import com.kodilla.kodilla.diplomaBackend.domain.enums.UserRole;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User {

    @Column(name = "ID", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@NotNull
    @Id
    private long id;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "MAIL")
    private String mail;

    @Column(name = "FIRSTNAME")
    private String firstname;

    @Column(name = "LASTNAME")
    private String lastname;

    @OneToMany(
            targetEntity = Rent.class,
            mappedBy = "user",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    private List<Rent> listOfRents = new ArrayList<>();

    @OneToMany(
            targetEntity = LogHistory.class,
            mappedBy = "user",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    private List<LogHistory> logHistory;

    @Column(name = "ROLE")
    private UserRole role;

    public User(long id, String login, String password, String mail, String firstname, String lastname, List<Rent> listOfRents, List<LogHistory> logHistory, UserRole role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.mail = mail;
        this.firstname = firstname;
        this.lastname = lastname;
        this.listOfRents = listOfRents;
        this.logHistory = logHistory;
        this.role = role;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Rent> getListOfRents() {
        return listOfRents;
    }

    public void setListOfRents(List<Rent> listOfRents) {
        this.listOfRents = listOfRents;
    }

    public List<LogHistory> getLogHistory() {
        return logHistory;
    }

    public void setLogHistory(List<LogHistory> logHistory) {
        this.logHistory = logHistory;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
