package com.example.Hospital_site_2022.Services;


import com.example.Hospital_site_2022.Entity.Diagnosis;
import com.example.Hospital_site_2022.Entity.Hospital;
import com.example.Hospital_site_2022.Entity.Patient;
import com.example.Hospital_site_2022.Repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PatientService {

    private final PatientRepository patientRepository;


    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public int createPatient(Hospital hospital, String name,
                             String surname, String residence,
                             String mail, String number) {
        try {
            patientRepository.save(new Patient(hospital, name, surname, residence, mail, number));
            return 1;
        } catch (Exception e){
            return -1;
        }

    }

    //если запрашивать через поля
    //1-всё хорошо
    //0-пользователя не найдено
    //-1 ошибка
    public int updatePatient(Long id,  Hospital hospital, String name,
                             String surname, String residence,
                             String mail, String number) {
        if(patientRepository.findById(id).isPresent()) {
            try {
                patientRepository.save(new Patient(
                        id, hospital, name, surname, residence, mail, number));
                return 1;
            } catch (Exception e){
                return -1;
            }
        }
        return 0;
    }

    //если запрашивать через объект
    public int updatePatient(Patient patient) {
        if(patientRepository.findById(patient.getId()).isPresent()) {
            try {
                patientRepository.save(patient);
                return 1;
            } catch (Exception e){
                return -1;
            }
        }
        return 0;
    }

    public int deletePatient(Long id) {
        patientRepository.deleteById(id);
        return 1;
    }


    public Patient getPatient(Long id) {
        return patientRepository.findById(id).orElseThrow();
    }

    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }


}
