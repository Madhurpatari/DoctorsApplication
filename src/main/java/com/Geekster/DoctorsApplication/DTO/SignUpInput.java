package com.Geekster.DoctorsApplication.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpInput {

    @NotBlank(message = "Patient FirstName is required")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    private String userFirstName;

    private String userLastName;

    @Email(message = "Invalid email address")
    private String userEmail;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    /*
    This regular expression enforces the following rules for a strong password:
    At least one letter (uppercase or lowercase) is required
    At least one digit is required
    At least one special character from the set @$!%*#?& is required
    The password must be at least 8 characters long
    */
    private String userPassword;

    @Size(max = 12)
    @Pattern(regexp = "\\+\\d{2}-\\d{10}", message = "Phone number must be in the format +xx-xxxxxxxxxx")
    private String userContact;
}
