package com.example.Repository;

import com.example.Entity.VerificationToken;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Long> {

    @Query("select v from VerificationToken v where v.token = ?1")
    VerificationToken findByToken(String token);
    @Transactional
    @Modifying
    @Query("delete from VerificationToken v where v.token = ?1")
    void removeByToken(String token);
}
