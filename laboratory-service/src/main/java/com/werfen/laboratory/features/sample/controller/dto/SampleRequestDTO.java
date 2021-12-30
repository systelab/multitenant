package com.werfen.laboratory.features.sample.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class SampleRequestDTO {

    @NotNull
    @Size(min = 1, max = 255)
    @Schema(description = "Barcode", required = true, example = "00000001")
    private String barcode;

    @NotNull
    private UUID patientId;

    private List<UUID> tests =new ArrayList<>();

}