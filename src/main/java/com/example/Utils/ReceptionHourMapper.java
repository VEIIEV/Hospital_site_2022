package com.example.Utils;


import com.example.Repository.DoctorRepository;
import com.example.DTO.ReceptionHourDTO;
import com.example.Entity.Doctor;
import com.example.Entity.ReceptionHour;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ReceptionHourMapper {

    private final DoctorRepository doctorRepository;


    public ReceptionHourMapper(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public ReceptionHourDTO toDTO(ReceptionHour receptionHour){
        return new ReceptionHourDTO(
                receptionHour.getId(),
                receptionHour.getDoctor().getId(),
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
        return new ReceptionHour(
                doctor,
                dateTime,
                receptionHourDTO.getStatus()
        );
    }



}
