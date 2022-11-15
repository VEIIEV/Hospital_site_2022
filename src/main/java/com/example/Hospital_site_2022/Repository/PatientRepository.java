package com.example.Hospital_site_2022.Repository;

import com.example.Hospital_site_2022.Entity.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {
}
