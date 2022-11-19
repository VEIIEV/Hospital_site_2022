package com.example.Hospital_site_2022.Repository;

import com.example.Hospital_site_2022.Entity.Diagnosis;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface DiagnosisRepository extends CrudRepository<Diagnosis, Long> {

    @Query("select d from Diagnosis d inner join d.patientCards patientCards where patientCards.id = ?1")
    Set<Diagnosis> findAllByPatientCards_id(Long id);
}
