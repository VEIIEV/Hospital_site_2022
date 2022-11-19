package com.example.Hospital_site_2022.Repository;

import com.example.Hospital_site_2022.Entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserRepository<T extends User> extends CrudRepository<T, Long> {

    @Override
    Iterable<T> findAllById(Iterable<Long> longs);

    @Modifying
    void deleteAllBy();



}
