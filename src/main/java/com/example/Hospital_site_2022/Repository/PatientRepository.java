package com.example.Hospital_site_2022.Repository;

import com.example.Hospital_site_2022.Entity.Doctor;
import com.example.Hospital_site_2022.Entity.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {
    public List<Patient>  findAll();
    Optional<Patient> findById(Long id);

}
