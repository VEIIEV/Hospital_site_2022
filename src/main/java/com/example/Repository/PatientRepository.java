package com.example.Repository;

import com.example.Entity.Patient;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public interface PatientRepository extends UserRepository<Patient> {
    @Transactional
    @Modifying
    @Query(value = "update hospital.patient p set hospital_id = 2 where patient_id=:id", nativeQuery = true)
    void updateHospital(@Param("id") Long id);
    List<Patient>  findAll();

    @Query("select p from Patient p")
    List<Patient>  findAll(Sort sort);

    @Modifying
    void deleteAllBy();

     //List<Patient> findAllByRequestedParamByRequestedSort(String sort, String title)

    Optional<Patient> findById(Long id);

}
