package com.pm.patientservice.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class PatientRequestDTO {
    @NotBlank(message = "name is required")
    @Size(min = 2,max = 100,message = "name can't exceed 100 characters")
    private String name;

    @NotBlank(message = "email is required")
    @Email(message = "Email should be valid")
    @Column(unique = true)
    private String email;

    @NotNull(message = "address is required")
    @Size(min = 5,max = 100,message = "address must be have 5 character and max 100 character")
    private String address;

    @NotNull(message = "gender is required")
    private String gender;

    @NotNull(message = "date of birth required")
    private String dateOfBirth;

    @NotNull(message = "register date is required")
    private String registeredDate;

}
