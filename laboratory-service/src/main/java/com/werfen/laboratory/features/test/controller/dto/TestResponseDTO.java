package com.werfen.laboratory.features.test.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class TestResponseDTO {

    private UUID id;

    private ZonedDateTime activationDate;

    @Schema(description = "Test code", required = true, example = "GLU1212")
    private String code;

    @Schema(description = "LOINC code", required = true, example = "GLU1212")
    private String loincCode;

    @Schema(description = "Test name", required = true, example = "Glucose")
    private String shortName;

}