package com.Geekster.DoctorsApplication.Model;

import com.Geekster.DoctorsApplication.Model.Enum.Specialization;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;
    private String doctorFirstName;
    private String doctorLastName;
    private String doctorEmail;
    private String doctorPassword;
    @Enumerated(EnumType.STRING)
    private Specialization specialization;
    private String doctorContact;
    @OneToMany(mappedBy = "doctor") //mapped by here used to get rid of join table
    List<Appointment> appointments;

    public Doctor(String doctorFirstName, String doctorLastName, String doctorEmail, String doctorPassword, Specialization specialization, String doctorContact) {
        this.doctorFirstName = doctorFirstName;
        this.doctorLastName = doctorLastName;
        this.doctorEmail = doctorEmail;
        this.doctorPassword = doctorPassword;
        this.specialization = specialization;
        this.doctorContact = doctorContact;
    }
}
