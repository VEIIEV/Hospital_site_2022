package com.example.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reception_hour")
@AllArgsConstructor
@NoArgsConstructor
public class ReceptionHour {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reception_hour_gen")
    @SequenceGenerator(name="reception_hour_gen", sequenceName="reception_hour_seq")    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;



    //dateFormat:  yyyy-mm-ddThh:mm:ssZ like 2011-12-03T10:15:30-03:00
    //DateTimeFormatter.ISO_DATE_TIME

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    //4 варианта, 1)не востребовано,2) завершено,3) не назначено, 4)назначено,

    @Column(name = "status")
    private int status=3;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public ReceptionHour(Doctor doctor, LocalDateTime dateTime, int status) {
        this.doctor = doctor;
        this.dateTime = dateTime;
        this.status = status;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}