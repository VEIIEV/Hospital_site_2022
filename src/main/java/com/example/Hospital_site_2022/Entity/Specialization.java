package com.example.Hospital_site_2022.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "specialization")
@Data
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="name")
    private String name;
}