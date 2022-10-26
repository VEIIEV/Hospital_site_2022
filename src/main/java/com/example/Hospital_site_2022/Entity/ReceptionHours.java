package com.example.Hospital_site_2022.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "reception_hours")
@Data
public class ReceptionHours {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column (name = "status", nullable = false)
    private String status;

    @Column (name = "time")
    private Calendar time;

    //связали расписание приёмов с врачом
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    //связали расписание приёмов с пациентами
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

}

//Таблица = график посещения, содержащая список записей и их статус