package com.werfen.laboratory.features.user.controller.dto;

import com.werfen.laboratory.features.user.model.User;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User fromRequestDTO(UserRequestDTO dto);

    UserResponseDTO toResponseDTO(User user);
}
