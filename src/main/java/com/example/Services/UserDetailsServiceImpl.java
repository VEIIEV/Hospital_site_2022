package com.example.Services;

import com.example.Entity.Doctor;
import com.example.Entity.Patient;
import com.example.Entity.User;
import com.example.Repository.DoctorRepository;
import com.example.Repository.HospitalRepository;
import com.example.Repository.PatientRepository;
import com.example.Repository.UserRepository;
import com.example.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    HospitalRepository hospitalRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public <T extends User> User saveUser(User user) throws IllegalArgumentException {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null || user.getUserRole()==null) {
            System.out.println("ex");
            throw new IllegalArgumentException();
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if(user.getUserRole()== UserRole.ROLE_PATIENT) {
            Patient patient = new Patient(user);
            patient.setHospital(hospitalRepository.findById(1L).get());
            patientRepository.save(patient);
            System.out.println("check p");
            return patient;
        }
        if(user.getUserRole()==UserRole.ROLE_DOCTOR){
            Doctor doctor = new Doctor(user);
            doctorRepository.save(doctor);
            System.out.println("check d ");
            return doctor;
        }
        return user;
    }

}
