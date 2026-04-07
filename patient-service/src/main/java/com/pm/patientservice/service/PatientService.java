package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.model.Patient;

import java.util.List;

public interface PatientService {

    List<PatientResponseDTO> getAllPatients();
}
