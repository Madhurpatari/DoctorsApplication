package com.Geekster.DoctorsApplication.DTO;

import com.Geekster.DoctorsApplication.Model.Enum.Specialization;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorSignUpInput {
    private String doctorFirstName;
    private String doctorLastName;
    private String doctorEmail;
    private String doctorPassword;
    @Enumerated(EnumType.STRING)
    private Specialization specialization;
    private String doctorContact;
}
