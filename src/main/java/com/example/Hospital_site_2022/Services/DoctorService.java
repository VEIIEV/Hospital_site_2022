package com.example.Hospital_site_2022.Services;

import com.example.Hospital_site_2022.DTO.PatientDTO;
import com.example.Hospital_site_2022.Entity.Doctor;
import com.example.Hospital_site_2022.Entity.Patient;
import com.example.Hospital_site_2022.Repository.DoctorRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public ResponseEntity<Doctor> createDoctor(Doctor doctor) {
        try {

            doctorRepository.save(doctor);
            Doctor createdDoctor = doctorRepository.findById(doctor.getId()).orElseThrow();
            return new ResponseEntity<>(createdDoctor, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Doctor> updateDoctor(Doctor doctor) {
        if (doctorRepository.findById(doctor.getId()).isPresent()) {
            try {
                doctorRepository.save(doctor);
                Doctor createdDoctor = doctorRepository.findById(doctor.getId()).orElseThrow();
                return new ResponseEntity<>(createdDoctor, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Integer> deleteDoctor(Long id) {
        if (doctorRepository.findById(id).isPresent()) {
            doctorRepository.deleteById(id);
            return new ResponseEntity<>(1, HttpStatus.OK);
        } else
            return new ResponseEntity<>(-1, HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<Doctor> getDoctor(Long id) {
        if (doctorRepository.findById(id).isPresent()) {
            Doctor doctor = doctorRepository.findById(id).orElseThrow();
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Doctor>> getAllDoctor(String sortMethod, String title) {
        List<Doctor> doctors;

        doctors = switch (sortMethod) {
            case "asc" -> doctorRepository.findAll(Sort.by(title).ascending());
            case "desc" -> doctorRepository.findAll(Sort.by(title).descending());
            default -> doctorRepository.findAll();
        };

        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }
}
