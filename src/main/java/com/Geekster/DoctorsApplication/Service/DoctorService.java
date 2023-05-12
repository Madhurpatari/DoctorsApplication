package com.Geekster.DoctorsApplication.Service;

import com.Geekster.DoctorsApplication.Model.Doctor;
import com.Geekster.DoctorsApplication.Repository.IDoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DoctorService {
    @Autowired
    IDoctorRepository doctorRepository;

    //Adding a doctor
    public  ResponseEntity<Doctor> addDoctor(Doctor doctor) {
        return new ResponseEntity<>(doctorRepository.save(doctor), HttpStatus.OK);
    }

    //Trying to get all doctors from patient controller
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
}
