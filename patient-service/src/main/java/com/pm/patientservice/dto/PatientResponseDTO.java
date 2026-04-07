package com.pm.patientservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PatientResponseDTO {
    private String id;
    private String name;
    private String email;
    private String gender;
    private String address;
    private String dateOfBirth;
}
