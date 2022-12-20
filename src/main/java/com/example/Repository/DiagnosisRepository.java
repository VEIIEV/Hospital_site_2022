package com.example.Repository;

import com.example.Entity.Diagnosis;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface DiagnosisRepository extends CrudRepository<Diagnosis, Long> {




    @Query("delete from Diagnosis d where 1=1")
    @Modifying
    void deleteAllBy();


    @Query("select d from Diagnosis d inner join d.patientCards patientCards where patientCards.id = ?1")
    Set<Diagnosis> findAllByPatientCards_id(Long id);

    @Query("select d from Diagnosis d where d.name in ?1")
    Set<Diagnosis> findAllByNameIn(Set<String> names);

    List<Diagnosis> findAll();

    List<Diagnosis> findAll(Sort sort);

    @Query("select d from Diagnosis d where d.name = ?1")
    Optional<Diagnosis> findByName(String diagnosis);
}
