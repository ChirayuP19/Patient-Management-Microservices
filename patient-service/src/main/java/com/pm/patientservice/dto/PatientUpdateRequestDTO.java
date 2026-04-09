package com.pm.patientservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientUpdateRequestDTO {

    @Size(min = 2, max = 100, message = "name can't exceed 100 characters")
    private String name;

    @Email(message = "Email should be valid")
    private String email;

    @Size(min = 5, max = 100, message = "address must have 5 characters and max 100 characters")
    private String address;

    private String gender;

    private String dateOfBirth;

    private String registeredDate;
}
