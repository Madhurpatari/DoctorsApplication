package com.Geekster.DoctorsApplication.Controller;

import com.Geekster.DoctorsApplication.DTO.SignInInput;
import com.Geekster.DoctorsApplication.DTO.SignInOutput;
import com.Geekster.DoctorsApplication.DTO.PatientSignUpInput;
import com.Geekster.DoctorsApplication.DTO.SignUpOutput;
import com.Geekster.DoctorsApplication.Model.AppointmentKey;
import com.Geekster.DoctorsApplication.Model.Doctor;
import com.Geekster.DoctorsApplication.Service.AuthenticationService;
import com.Geekster.DoctorsApplication.Service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @Autowired
    private AuthenticationService authenticationService;

    //Sign-up
    @PostMapping("/sign-up")
    public ResponseEntity<SignUpOutput> signup(@Valid  @RequestBody PatientSignUpInput patientSignUpInput){
        return patientService.signUp(patientSignUpInput);
    }

    //Sign-in
    @PostMapping("/sign-in")
    public ResponseEntity<SignInOutput> signIn(@RequestBody SignInInput signInInput){
        return patientService.signIn(signInInput);
    }

    //Get all doctors
    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>> getAllDoctors(@RequestParam String userEmail, @RequestParam String token){
        HttpStatus status;
        List<Doctor> allDoctors = null;
        if(authenticationService.authenticate(userEmail, token)){
            allDoctors = patientService.getAllDoctors();
            status = HttpStatus.OK;
        }else{
            status = HttpStatus.FORBIDDEN;
        }
        return new ResponseEntity<>(allDoctors, status);
    }

    //Delete an appointment
    @DeleteMapping("/cancelAppointment")
    ResponseEntity<String> cancelAppointment(@RequestParam String userEmail , @RequestParam String token , @RequestBody AppointmentKey appointmentKey){
        HttpStatus status;
        String response =null;
        if(authenticationService.authenticate(userEmail,token)){
            patientService.cancelAppointment(appointmentKey);
            response = "Appointment cancelled successfully..!!";
            status = HttpStatus.OK;
        }else{
            response = "Invalid user..try again!!";
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(response,status);
    }
}
