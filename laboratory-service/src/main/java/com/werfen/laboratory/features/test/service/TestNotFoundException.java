package com.werfen.laboratory.features.test.service;

import com.werfen.laboratory.core.handlers.exception.ApiExceptionCode;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@ApiExceptionCode(value = "ERR_TEST_NOT_FOUND", text = "Test not found")
public class TestNotFoundException extends RuntimeException {

    private final String id;

    public TestNotFoundException(UUID id) {
        super("test-not-found-" + id.toString());
        this.id = id.toString();
    }

    public String getTestId() {
        return id;
    }
}