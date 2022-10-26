package com.example.Hospital_site_2022.Entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "hospitals")
@Data
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "mail")
    private String mail;

    @Column(name = "address")
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hospital")
    private Set<Doctor> doctors;

    // многие ко многим
   // @ManyToMany
   // @JoinTable(
   //         name ="doctors_list",
   //         joinColumns =  @JoinColumn(name = "doctor_id"),
   //         inverseJoinColumns = @JoinColumn(name = "hospital_id"))
   // private Set<Doctor> doctors = new java.util.LinkedHashSet<>();




    //Таблица = больница(id, название, конатактные заданые, адрес???)
}
