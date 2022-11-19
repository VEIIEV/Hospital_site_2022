package com.example.Hospital_site_2022.Repository;

import com.example.Hospital_site_2022.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface UserRepository<T extends User> extends CrudRepository<T, Long> {


}
