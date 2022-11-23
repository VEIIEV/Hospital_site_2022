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
import org.springframework.beans.factory.annotation.Qualifier;
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

    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null || user.getUserRole()==null) {
            return false;
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if(user.getUserRole()== UserRole.PATIENT) {
            Patient patient = new Patient(user);
            patient.setHospital(hospitalRepository.findById(1L).get());
            patientRepository.save(patient);
        }
        if(user.getUserRole()==UserRole.DOCTOR){
            Doctor doctor = new Doctor(user);
            doctorRepository.save(doctor);
        }
        return true;
    }

}
