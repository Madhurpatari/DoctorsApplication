package com.Geekster.DoctorsApplication.Service;

import com.Geekster.DoctorsApplication.DTO.*;
import com.Geekster.DoctorsApplication.Model.Appointment;
import com.Geekster.DoctorsApplication.Model.AuthenticationToken;
import com.Geekster.DoctorsApplication.Model.Doctor;
import com.Geekster.DoctorsApplication.Model.Patient;
import com.Geekster.DoctorsApplication.Repository.IDoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;


@Service
public class DoctorService {
    @Autowired
    IDoctorRepository doctorRepository;
    @Autowired
    private AuthenticationService authenticationService;

    public ResponseEntity<SignUpOutput> signUp(DoctorSignUpInput doctorSignUpInput) {
        //check if Doctor already exist or not
        Doctor doctor = doctorRepository.findFirstByDoctorEmail(doctorSignUpInput.getDoctorEmail());


        if (doctor != null) {  //this means Doctor with this email already exists in the database
            throw new IllegalStateException("Doctor with this email already exist..try sign-in");
        }

        //Encryption of password
        String hashedPassword = DigestUtils.md5DigestAsHex(doctorSignUpInput.getDoctorPassword().getBytes());

        //save the user as a new doctor
        doctor = new Doctor(
                doctorSignUpInput.getDoctorFirstName(),
                doctorSignUpInput.getDoctorLastName(),
                doctorSignUpInput.getDoctorEmail(),
                hashedPassword,
                doctorSignUpInput.getSpecialization(),
                doctorSignUpInput.getDoctorContact()
        );
        //save doctor in database
        doctorRepository.save(doctor);

        //Create and save token for doctor
        AuthenticationToken token = new AuthenticationToken(doctor);
        authenticationService.saveToken(token);

        return new ResponseEntity<>(new SignUpOutput("Doctor registered successfully..!!"), HttpStatus.OK);
    }

    //Sign-in service for doctor
    public ResponseEntity<SignInOutput> signIn(SignInInput signInInput) {
        //Get doctor email and check if it's already there in the database or not
        Doctor doctor = doctorRepository.findFirstByDoctorEmail(signInInput.getEmail());

        if (doctor == null) { //if doctor is null it means there is no doctor registered with this email
            throw new IllegalStateException("No doctor is registered with this email..try sign-up!!");
        }

        //Encrypt the doctor password to check if this encrypted password is exists in the database
        String encryptedPass = DigestUtils.md5DigestAsHex(signInInput.getPassword().getBytes());

        //Match it with already existing passwords
        boolean isPasswordValid = encryptedPass.equals(doctor.getDoctorPassword());

        //if it is not valid throw exception
        if (!isPasswordValid) {
            throw new IllegalStateException("User Invalid..try sign-in again!!");
        }

        //figure out the doctor token
        AuthenticationToken authenticationToken = authenticationService.getToken(doctor);

        return new ResponseEntity<>(new SignInOutput(authenticationToken.getToken()), HttpStatus.ACCEPTED);
    }

    //Trying to get all doctors from patient controller
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    //Trying to get all appointments corresponds to particular doctorID
    public List<Appointment> getMyAppointments(Long doctorId) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
        if (optionalDoctor.isPresent()) {
            return optionalDoctor.get().getAppointments();
        } else {
            throw new IllegalStateException("The doctor does not exist");
        }
    }
}



