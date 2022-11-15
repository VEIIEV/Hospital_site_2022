package com.example.Hospital_site_2022.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Hospital extends CrudRepository<Hospital, Long> {
}
