package com.werfen.laboratory.features.sample.controller.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class PatientResponseDTO {
    private UUID id;

    private String name;
}
