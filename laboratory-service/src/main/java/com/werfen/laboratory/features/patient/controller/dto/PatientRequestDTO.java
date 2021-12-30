package com.werfen.laboratory.features.patient.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PatientRequestDTO {

    @NotNull
    @Size(min = 1, max = 255)
    @Schema(description = "Patient name", required = true, example = "Paul Smith")
    private String name;

}