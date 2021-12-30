package com.werfen.laboratory.features.patient.service;

import com.werfen.laboratory.core.handlers.exception.ApiExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
@ApiExceptionCode(value = "ERR_PATIENT_NOT_FOUND", text = "Patient not found")
public class PatientNotFoundException extends RuntimeException {

    private final String id;

    public PatientNotFoundException(UUID id) {
        super("patient-not-found-" + id.toString());
        this.id = id.toString();
    }

    public String getPatientId() {
        return id;
    }
}