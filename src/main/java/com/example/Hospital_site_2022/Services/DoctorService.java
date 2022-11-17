package com.example.Hospital_site_2022.Services;

import com.example.Hospital_site_2022.DTO.DoctorDTO;
import com.example.Hospital_site_2022.Entity.Doctor;
import com.example.Hospital_site_2022.Repository.DoctorRepository;
import com.example.Hospital_site_2022.Utils.DoctorMapper;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    public DoctorService(DoctorRepository doctorRepository, DoctorMapper doctorMapper) {
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
    }

    public ResponseEntity<DoctorDTO> createDoctor(DoctorDTO doctorDTO) {
        try {
            Doctor doctor = doctorMapper.toDoctor(doctorDTO);
            doctorRepository.save(doctor);
            Doctor createdDoctor = doctorRepository.findById(doctor.getId()).orElseThrow();
            return new ResponseEntity<>(doctorMapper.toDTO(createdDoctor), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Doctor> updateDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = doctorMapper.toDoctor(doctorDTO);
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
