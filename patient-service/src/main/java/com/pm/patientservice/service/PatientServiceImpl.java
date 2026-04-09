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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService{

    private final PatientRepository patientRepository;

    @Override
    public Page<PatientResponseDTO> getAllPatients(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Patient> patientPage = patientRepository.findAll(pageable);
        return patientPage.map(PatientMapper::toDTO);
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
        if(requestDTO.getEmail()!=null){
            boolean email = patientRepository.existsByEmail(requestDTO.getEmail());
            if(email)
                throw new EmailAlreadyExistsException(requestDTO.getEmail()+" already exist ");
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

    @Override
    public void deletePatient(UUID patientId) {
        patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with ID:: " + patientId));
        patientRepository.deleteById(patientId);
    }
}
