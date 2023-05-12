package com.Geekster.DoctorsApplication.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
public class AuthenticationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;
    private String token;
    private LocalDate tokenCreationDate;
    @OneToOne
    @JoinColumn(nullable = false, name = "Patient_ID")
    private Patient patient;

    //Parameterized Constructor
    public AuthenticationToken(Patient patient){
        this.patient = patient;
        this.tokenCreationDate = LocalDate.now();
        this.token = UUID.randomUUID().toString(); //will generate random token in string format
    }
}
