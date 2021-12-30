package com.werfen.laboratory.features.user.controller.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.werfen.laboratory.features.user.model.UserRole;
import lombok.Data;

@Data
public class UserRequestDTO {

    @NotNull
    @Size(min = 1, max = 255)
    private String surname;

    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    @NotNull
    @Size(min = 1, max = 10)
    private String login;

    @Size(min = 1, max = 256)
    private String password;

    @NotNull
    private UserRole role = UserRole.USER;
}
