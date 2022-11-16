package com.example.Hospital_site_2022.Repository;

import com.example.Hospital_site_2022.Entity.Doctor;
import com.example.Hospital_site_2022.Entity.Patient;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {
    List<Patient>  findAll();

    List<Patient>  findAll(Sort sort);

    List<Patient> findAllByOrderByIdAsc();
    List<Patient> findAllByOrderByIdDesc();
    List<Patient> findAllByOrderByNameAsc();
    List<Patient> findAllByOrderByNameDesc();


     //List<Patient> findAllByRequestedParamByRequestedSort(String sort, String title)

    Optional<Patient> findById(Long id);

}
