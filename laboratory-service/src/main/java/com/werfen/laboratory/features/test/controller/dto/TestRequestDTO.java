package com.werfen.laboratory.features.test.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Data
public class TestRequestDTO {

    private ZonedDateTime activationDate;

    @Size(max = 15)
    @Schema(description = "Test code", required = true, example = "GLU1212")
    private String code;

    @Size(max = 15)
    @Schema(description = "LOINC code", required = true, example = "GLU1212")
    private String loincCode;

    @NotNull
    @Size(min = 1, max = 255)
    @Schema(description = "Test name", required = true, example = "Glucose")
    private String shortName;

}