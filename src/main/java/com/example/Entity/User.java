package com.example.Entity;


import com.example.enums.UserRole;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "User_table")
@Inheritance(strategy = InheritanceType.JOINED)
public class User  implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
    @SequenceGenerator(name = "user_gen", sequenceName = "user_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    @NotEmpty(message = "login Field can't be empty!")
    @Column(name = "user_name")
    private String userName;

    @NotEmpty(message = "password Field can't be empty!")
    @Column(name = "password")
    private String password;

    @Column(name = "token")
    private String token;

    @Column(name = "name")
    private String name;

    @NotEmpty()
    @Email(message = "its not a mail")
    @Column(name = "mail")
    private String mail;

    @Column(name = "number")
    private String number;


    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] image;




    public User(Long id, String userName, String password, String token, String name, String mail, String number) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.token = token;
        this.name = name;
        this.mail = mail;
        this.number = number;
    }

    public User(String userName, String password, String token, String name, String mail, String number) {
        this.userName = userName;
        this.password = password;
        this.token = token;
        this.name = name;
        this.mail = mail;
        this.number = number;
    }

    public User(UserRole userRole, String userName, String password, String name, String mail, String number) {
        this.userRole = userRole;
        this.userName = userName;
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

    public User(User user) {
        this.userName=user.getUserName();
        this.password=user.getPassword();
        this.mail=user.getMail();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String login) {
        this.userName = login;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        return authorities;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
