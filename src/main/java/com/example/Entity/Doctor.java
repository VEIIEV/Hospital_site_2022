package com.example.Entity;

import com.example.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "doctor")
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "doctor_id")
@Polymorphism(type = PolymorphismType.EXPLICIT)
public class Doctor extends User {


    @ManyToOne
    @JoinColumn(name = "specialisation_id")
    private Specialisation specialisation;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReceptionHour> receptionHours;


    @Column(name = "surname")
    private String surname;

    @Column(name = "seniority")
    private String seniority;





    public Doctor(Specialisation specialisation, String name, String surname, String mail, String number, String seniority) {
        super(name, mail, number);
        this.specialisation = specialisation;
        this.surname = surname;
        this.seniority = seniority;
        super.setUserRole(UserRole.ROLE_DOCTOR);
    }

    public Doctor(String login, String password, String token, String name, String mail, String number, Specialisation specialisation,  String surname,   String seniority) {
        super(login, password, token, name, mail, number);
        this.specialisation = specialisation;
        this.surname = surname;
        this.seniority = seniority;
        super.setUserRole(UserRole.ROLE_DOCTOR);
    }

    public Doctor(User user) {
        super(user);
        setUserRole(UserRole.ROLE_DOCTOR);
    }

    public List<ReceptionHour> getReceptionHours() {
        return receptionHours;
    }

    public void setReceptionHours(List<ReceptionHour> receptionHours) {
        this.receptionHours = receptionHours;
    }

    public Specialisation getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(Specialisation specialisation) {
        this.specialisation = specialisation;
    }



    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSeniority() {
        return seniority;
    }


    public void setSeniority(String seniority) {
        this.seniority = seniority;
    }

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("DOCTOR"));
        return authorities;
    }

}