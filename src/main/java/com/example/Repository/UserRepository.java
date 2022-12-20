package com.example.Repository;

import com.example.Entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository<T extends User> extends CrudRepository<T, Long> {

    @Override
    Iterable<T> findAllById(Iterable<Long> longs);

    @Modifying
    void deleteAllBy();


    @Query("select u from User u where u.userName = ?1")
    User findByUsername(String userName);


    @Query("select u from User u where u.mail=?1")
    User findByEmail(String email);
}
