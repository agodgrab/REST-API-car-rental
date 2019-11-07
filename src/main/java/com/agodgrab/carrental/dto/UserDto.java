package com.kodilla.kodilla.diplomaBackend.dto;

import com.kodilla.kodilla.diplomaBackend.domain.enums.UserRole;

public class UserDto {

    private long id;

    private String login;

    private String password;

    private String mail;

    private String firstname;

    private String lastname;

    private UserRole role;

    public UserDto(long id, String login, String password, String mail, String firstname, String lastname, UserRole role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.mail = mail;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
    }

    public UserDto() {
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public UserRole getRole() {
        return role;
    }
}
