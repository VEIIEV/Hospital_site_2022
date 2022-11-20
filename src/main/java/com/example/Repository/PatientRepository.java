package com.example.Repository;

import com.example.Entity.Patient;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PatientRepository extends UserRepository {
    List<Patient>  findAll();

    List<Patient>  findAll(Sort sort);

    @Modifying
    void deleteAllBy();

     //List<Patient> findAllByRequestedParamByRequestedSort(String sort, String title)

    Optional<Patient> findById(Long id);

}
