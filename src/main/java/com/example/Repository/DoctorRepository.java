package com.example.Repository;

import com.example.Entity.Doctor;
import com.example.Entity.Specialisation;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface DoctorRepository extends UserRepository<Doctor> {

    List<Doctor> findAll();

    List<Doctor> findAll(Sort sort);

    @Modifying
    void deleteAllBy();
    @Query("select d.name, d.surname, d.id from Doctor d where d.specialisation.name = ?1")
    Set<String> findBySpecialisation_Name(String name);

    @Query("select d from Doctor d where d.specialisation = ?1")
    List<Doctor> findAllBySpecialisation(Specialisation specialisation);


    Optional<Doctor> findById(Long id);


    @Query("select d from Doctor d where d.userName = ?1")
    Optional<Doctor> findDoctorsByUsername(String name);
}
