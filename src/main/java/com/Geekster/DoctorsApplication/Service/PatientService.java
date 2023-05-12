package com.Geekster.DoctorsApplication.Service;

import com.Geekster.DoctorsApplication.DTO.SignInInput;
import com.Geekster.DoctorsApplication.DTO.SignInOutput;
import com.Geekster.DoctorsApplication.DTO.SignUpInput;
import com.Geekster.DoctorsApplication.DTO.SignUpOutput;
import com.Geekster.DoctorsApplication.Model.AuthenticationToken;
import com.Geekster.DoctorsApplication.Model.Doctor;
import com.Geekster.DoctorsApplication.Model.Patient;
import com.Geekster.DoctorsApplication.Repository.IPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    private IPatientRepository patientRepo;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    DoctorService doctorService;


    //sign-up service
    public ResponseEntity<SignUpOutput> signUp(SignUpInput signUpInput) {
        //check if patient already exist or not
        Patient patient =patientRepo.findFirstByPatientEmail(signUpInput.getUserEmail());


        if(patient != null){  //this means patient with this email already exists in the database
            throw new IllegalStateException("Patient with this email already exist..try sign-in");
        }

        //Encryption of password
        String hashedPassword= DigestUtils.md5DigestAsHex(signUpInput.getUserPassword().getBytes());

        //save the user as a new patient
        patient = new Patient(
                signUpInput.getUserFirstName(),
                signUpInput.getUserLastName(),
                signUpInput.getUserEmail(),
                hashedPassword,
                signUpInput.getUserContact()
        );
        //save patient in database
        patientRepo.save(patient);

        //Create and save token
        AuthenticationToken token = new AuthenticationToken(patient);
        authenticationService.saveToken(token);

        return new ResponseEntity<>(new SignUpOutput("Patient registered successfully..!!"),HttpStatus.OK);
    }

    //sign-in service
    public ResponseEntity<SignInOutput> signIn(SignInInput signInInput) {

        //Get patient email and check if it's already there in the database or not
        Patient patient = patientRepo.findFirstByPatientEmail(signInInput.getPatientEmail());

        if(patient == null){ //if patient is null it means there is no patient registered with this email
            throw new IllegalStateException("No patient is registered with this email..try sign-up!!");
        }

        //Encrypt the patient password to check if this encrypted password is exists in the database
        String encryptedPass = DigestUtils.md5DigestAsHex(signInInput.getPatientPassword().getBytes());

        //Match it with already existing passwords
        boolean isPasswordValid = encryptedPass.equals(patient.getPatientPassword());

        //if it is not valid throw exception
        if(!isPasswordValid){
            throw new IllegalStateException("User Invalid..try sign-in again!!");
        }

        //figure out the token
        AuthenticationToken authenticationToken = authenticationService.getToken(patient);

        return new ResponseEntity<>(new SignInOutput(authenticationToken.getToken()), HttpStatus.ACCEPTED);

    }

    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }
}
