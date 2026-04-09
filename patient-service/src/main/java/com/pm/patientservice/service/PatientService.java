package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface PatientService {

    Page<PatientResponseDTO> getAllPatients(int page, int size, String sortBy, String sortDir);
    PatientResponseDTO createPatient(PatientRequestDTO requestDTO);
    PatientResponseDTO updatePatient(UUID id, PatientRequestDTO requestDTO);
    void deletePatient(UUID patientId);
}
