package com.example.Entity;


import com.example.enums.UserRole;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "User")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
    @SequenceGenerator(name = "user_gen", sequenceName = "user_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "token")
    private String token;

    @Column(name = "name")
    private String name;

    @Column(name = "mail")
    private String mail;

    @Column(name = "number")
    private String number;




    public User(Long id, String login, String password, String token, String name, String mail, String number) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.token = token;
        this.name = name;
        this.mail = mail;
        this.number = number;
    }

    public User(String login, String password, String token, String name, String mail, String number) {
        this.login = login;
        this.password = password;
        this.token = token;
        this.name = name;
        this.mail = mail;
        this.number = number;
    }

    public User(UserRole userRole, String login, String password, String name, String mail, String number) {
        this.userRole = userRole;
        this.login = login;
        this.password = password;
        this.name = name;
        this.mail = mail;
        this.number = number;
    }

    public User(String name, String mail, String number) {
        this.name = name;
        this.mail = mail;
        this.number = number;
    }

    public User(Long id, String name, String mail, String number) {
        this.name = name;
        this.mail = mail;
        this.number = number;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
