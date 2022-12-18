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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Base64;


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
        return user;
    }

    public <T extends User> User saveUser(User user) throws IllegalArgumentException {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null || user.getUserRole()==null) {
            System.out.println("ex");
            throw new IllegalArgumentException();
        }
        try {
            //почему с 1, получаю ошибку  No enum constant com.example.enums.UserRole.PATIENT
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            if (user.getUserRole() == UserRole.ROLE_PATIENT) {
                Patient patient = new Patient(user);
                Patient patientFromDB =patientRepository.save(patient);
                patientRepository.updateHospital(patientFromDB.getId());
                System.out.println("check p");
                return patient;
            }
            if (user.getUserRole() == UserRole.ROLE_DOCTOR) {
                Doctor doctor = new Doctor(user);
                doctorRepository.save(doctor);
                System.out.println("check d ");
                return doctor;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Transactional
    public void updateImg(MultipartFile multipartImage, Principal principal) throws IOException {

        User user= userRepository.findByUsername(principal.getName());
       // user.setImage(multipartImage.getBytes());
        user.setImage(Base64.getEncoder().encode(multipartImage.getBytes()));

        System.out.println(user.getImage().length);
        userRepository.save(user);

    }
}
