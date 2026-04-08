package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.exception.EmailAlreadyExistsException;
import com.pm.patientservice.exception.PatientNotFoundException;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService{

    private final PatientRepository patientRepository;

    @Override
    public List<PatientResponseDTO> getAllPatients() {
        List<Patient> patiens = patientRepository.findAll();
        return patiens.stream()
                .map(PatientMapper::toDTO).toList();
    }

    @Override
    public PatientResponseDTO createPatient(PatientRequestDTO requestDTO) {
        if(patientRepository.existsByEmail(requestDTO.getEmail())){
            throw new EmailAlreadyExistsException("Email :: "+requestDTO.getEmail()+" already exists"+requestDTO);
        }
        Patient patient = patientRepository.save(PatientMapper.toModel(requestDTO));
        return PatientMapper.toDTO(patient);
    }

    @Override
    @Transactional
    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO requestDTO) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient not found with ID:: " + id));
        if(patientRepository.existsByEmail(requestDTO.getEmail())){
            throw new EmailAlreadyExistsException("Email :: "+requestDTO.getEmail()+" already exists"+requestDTO);
        }
        if(requestDTO.getEmail()!=null){
            patient.setEmail(requestDTO.getEmail());
        }
        if(requestDTO.getName()!=null){
            patient.setName(requestDTO.getName());
        }
        if(requestDTO.getGender()!=null){
            patient.setGender(requestDTO.getGender());
        }
        if(requestDTO.getAddress()!=null){
            patient.setAddress(requestDTO.getAddress());
        }
        if(requestDTO.getDateOfBirth()!=null){
            patient.setDateOfBirth(LocalDate.parse(requestDTO.getDateOfBirth()));
        }
        patientRepository.save(patient);

        return PatientMapper.toDTO(patient);
    }
}
