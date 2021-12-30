package com.werfen.laboratory.features.user.service;

import com.werfen.laboratory.core.handlers.exception.ApiExceptionCode;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@ApiExceptionCode(value = "ERR_USER_NOT_FOUND", text = "User not found")
public class UserNotFoundException extends RuntimeException {
    private final String id;

    public UserNotFoundException(UUID id) {
        super("user-not-found-" + id.toString());
        this.id = id.toString();
    }

    public String getUserId() {
        return id;
    }


}
