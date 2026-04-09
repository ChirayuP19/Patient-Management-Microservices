package com.pm.patientservice.controller;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/patients")
@RequiredArgsConstructor
@Tag(name = "Patient",description = "API for manning Patients")
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    @Operation(summary = "Get Patients")
    public ResponseEntity<Page<PatientResponseDTO>> getAllPatients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        return ResponseEntity.ok(patientService.getAllPatients(page,size,sortBy,sortDir));
    }

    @PostMapping
    @Operation(summary = "Create Patient")
    public ResponseEntity<PatientResponseDTO> createPatient(
            @Valid @RequestBody PatientRequestDTO requestDTO) {
        return ResponseEntity.ok(patientService.createPatient(requestDTO));
    }

    @PatchMapping("/{patientId}")
    @Operation(summary = "Update Patient")
    public ResponseEntity<PatientResponseDTO> updatePatient(
            @PathVariable("patientId") UUID patientId,
            @Valid @RequestBody PatientRequestDTO requestDTO) {
        return ResponseEntity.ok(patientService.updatePatient(patientId, requestDTO));
    }

    @DeleteMapping("/{patientId}")
    @Operation(summary = "Delete Patient")
    public ResponseEntity<String> deleteById(@PathVariable("patientId") UUID patientId) {
        patientService.deletePatient(patientId);
        return ResponseEntity.ok("Patient successfully delete By ID:: " + patientId);
    }
}
