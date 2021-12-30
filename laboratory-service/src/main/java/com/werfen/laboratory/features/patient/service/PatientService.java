package com.werfen.laboratory.features.patient.service;

import com.werfen.laboratory.features.patient.repository.PatientRepository;
import com.werfen.laboratory.features.patient.model.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public Page<Patient> getByFilter(Pageable pageable) {
        return patientRepository.findAll(getPageableWithDefaultsSort(pageable));
    }

    private Pageable getPageableWithDefaultsSort(Pageable pageable) {
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                pageable.getSortOr(Sort.by("id").descending()));
    }


    public Patient getPatient(UUID patientId) {
        return this.patientRepository.findById(patientId).orElseThrow(() -> new PatientNotFoundException(patientId));
    }

    public Patient createPatient(Patient patient) {
        return this.patientRepository.save(patient);
    }

    public Patient updatePatient(UUID id, Patient patient) {
        return this.patientRepository.findById(id)
                .map(existing -> {
                    patient.setId(id);
                    return this.patientRepository.save(patient);
                }).orElseThrow(() -> new PatientNotFoundException(id));
    }

    public Patient removePatient(UUID id) {
        return this.patientRepository.findById(id)
                .map(existing -> {
                    patientRepository.delete(existing);
                    return existing;
                }).orElseThrow(() -> new PatientNotFoundException(id));
    }
}
