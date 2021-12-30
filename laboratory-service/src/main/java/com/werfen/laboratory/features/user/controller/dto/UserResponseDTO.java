package com.werfen.laboratory.features.user.controller.dto;

import com.werfen.laboratory.features.user.model.UserRole;
import lombok.Data;

import java.util.UUID;

@Data
public class UserResponseDTO {

    private UUID id;

    private String surname;
    private String name;
    private String login;
    private UserRole role = UserRole.USER;
}