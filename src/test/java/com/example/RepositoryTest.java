package com.example;


import com.example.Entity.Hospital;
import com.example.Repository.HospitalRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RepositoryTest {

    @Autowired private HospitalRepository hospitalRepository;

    @Test
    public void testAddHospital()   {
        Hospital hospital = new Hospital();
        hospital.setAddress("testAddress");
        hospital.setId(25L);
        hospital.setMail("test@mail.ru");
        hospital.setName("testName");
        hospital.setNumber("000000");
        Hospital backHospital =hospitalRepository.save(hospital);
        System.out.println(backHospital);
        Assert.isInstanceOf(Hospital.class, backHospital);
    }
}
