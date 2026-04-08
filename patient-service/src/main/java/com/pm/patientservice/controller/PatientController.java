package com.pm.patientservice.controller;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(
            @Valid @RequestBody PatientRequestDTO requestDTO) {
        return ResponseEntity.ok(patientService.createPatient(requestDTO));
    }

    @PatchMapping("/{patientId}")
    public ResponseEntity<PatientResponseDTO> updatePatient(
            @PathVariable("patientId") UUID patientId,
            @Valid @RequestBody PatientRequestDTO requestDTO){
        return ResponseEntity.ok(patientService.updatePatient(patientId,requestDTO));
    }
}
