package com.example.Hospital_site_2022.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "reception_hour")
@AllArgsConstructor
@NoArgsConstructor
public class ReceptionHour {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    // график приёма чётко привязан ко времени
    // первый приём с 8:00 до 8: 30
    // второй  приём с 8:35 до 9:35
    // и т.д каждый имеет свой sequenceNumber
    private int sequenceNumber;

    //3 варианта, не назначено, назначено, завершено.
    private int status;

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
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