package com.example.Repository;

import com.example.Entity.Hospital;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface HospitalRepository extends CrudRepository<Hospital, Long> {

    Optional<Hospital> findById(Long id);

    @Modifying
    void deleteAllBy();
}
