package com.werfen.laboratory.features.patient.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class PatientResponseDTO {

    private UUID id;

    private String name;

}