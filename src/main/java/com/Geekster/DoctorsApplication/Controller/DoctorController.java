package com.Geekster.DoctorsApplication.Controller;

import com.Geekster.DoctorsApplication.DTO.*;
import com.Geekster.DoctorsApplication.Model.Appointment;
import com.Geekster.DoctorsApplication.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    //sign-up for a doctor as a new doctor
    @PostMapping("/sign-up")
    public ResponseEntity<SignUpOutput> signUp(@RequestBody DoctorSignUpInput doctorSignUpInput){
        return doctorService.signUp(doctorSignUpInput);
    }

    //sign-in for doctor
    @PostMapping("/sign-in")
    public ResponseEntity<SignInOutput> signIn(@RequestBody SignInInput signInInput){
        return doctorService.signIn(signInInput);
    }

    //Get list of appointments for the Doctor corresponding to his doctor ID
    @GetMapping("/{doctorId}/appointments")
    public ResponseEntity<List<Appointment>> getMyAppointments(@PathVariable Long doctorId){
        List<Appointment> appointments = null;
        HttpStatus status;
        try{
            appointments = doctorService.getMyAppointments(doctorId);
            if(appointments.isEmpty()){
                 status = HttpStatus.NO_CONTENT;
            }else{
                status = HttpStatus.OK;
            }
        }catch (Exception exception){
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(appointments,status);
    }


}
