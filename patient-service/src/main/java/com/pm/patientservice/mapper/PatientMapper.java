package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.model.Patient;

import java.time.LocalDate;

public class PatientMapper {

    public static PatientResponseDTO toDTO(Patient patient){
        return PatientResponseDTO.builder()
                .id(patient.getId().toString())
                .name(patient.getName())
                .email(patient.getEmail())
                .address(patient.getAddress())
                .gender(patient.getGender())
                .dateOfBirth(patient.getDateOfBirth().toString())
                .build();
    }
    public static Patient toModel(PatientRequestDTO requestDTO){
        return Patient.builder()
                .name(requestDTO.getName())
                .email(requestDTO.getEmail())
                .address(requestDTO.getAddress())
                .gender(requestDTO.getGender())
                .dateOfBirth(LocalDate.parse(requestDTO.getDateOfBirth()))
                .registeredDate(LocalDate.parse(requestDTO.getRegisteredDate()))
                .build();
    }
}
