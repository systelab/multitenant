package com.werfen.laboratory.features.sample.service;

import com.werfen.laboratory.core.handlers.exception.ApiExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
@ApiExceptionCode(value = "ERR_PATIENT_NOT_FOUND", text = "Sample not found")
public class SampleNotFoundException extends RuntimeException {

    private final String id;

    public SampleNotFoundException(UUID id) {
        super("sample-not-found-" + id.toString());
        this.id = id.toString();
    }

    public String getSampleId() {
        return id;
    }
}