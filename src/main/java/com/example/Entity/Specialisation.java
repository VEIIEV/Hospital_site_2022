package com.example.Entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="specialisation" )
@AllArgsConstructor
@NoArgsConstructor
public class Specialisation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "spec_gen")
    @SequenceGenerator(name = "spec_gen", sequenceName = "spec_seq")    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "specialisation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Doctor> doctors;

    @Column(name="name")
    private String name;

    public Specialisation(Hospital hospital, String name) {
        this.hospital = hospital;
        this.name = name;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
