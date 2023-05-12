package com.Geekster.DoctorsApplication.Controller;

import com.Geekster.DoctorsApplication.Model.Doctor;
import com.Geekster.DoctorsApplication.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.Doc;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    //Add a doctor
    @PostMapping
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor){
        return doctorService.addDoctor(doctor);
    }
}
