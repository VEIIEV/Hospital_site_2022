package com.example.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import javax.persistence.*;
import java.util.List;

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
    }

    public Doctor(String login, String password, String token, String name, String mail, String number, Specialisation specialisation,  String surname,   String seniority) {
        super(login, password, token, name, mail, number);
        this.specialisation = specialisation;
        this.surname = surname;
        this.seniority = seniority;
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

}