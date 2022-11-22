package com.example.Utils;


import com.example.Entity.Patient;
import com.example.Repository.DoctorRepository;
import com.example.DTO.ReceptionHourDTO;
import com.example.Entity.Doctor;
import com.example.Entity.ReceptionHour;
import com.example.Repository.PatientRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ReceptionHourMapper {

    private final DoctorRepository doctorRepository;
    private  final PatientRepository patientRepository;


    public ReceptionHourMapper(DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public ReceptionHourDTO toDTO(ReceptionHour receptionHour){
        return new ReceptionHourDTO(
                receptionHour.getId(),
                receptionHour.getDoctor().getId(),
                receptionHour.getPatient().getId(),
                receptionHour.getDateTime().toString(),
                receptionHour.getStatus()
        );
    }

    //dateFormat:  yyyy-mm-ddThh:mm:ssZ like 2011-12-03T10:15:30-03:00
    //            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public ReceptionHour toReceptionHour(ReceptionHourDTO receptionHourDTO){
        String str = receptionHourDTO.getDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        Doctor doctor = doctorRepository.findById(receptionHourDTO.getDoctor_id()).get();
        Patient patient = patientRepository.findById(receptionHourDTO.getPatient_id()).get();
        return new ReceptionHour(
                doctor,
                patient,
                dateTime,
                receptionHourDTO.getStatus()
        );
    }



}
