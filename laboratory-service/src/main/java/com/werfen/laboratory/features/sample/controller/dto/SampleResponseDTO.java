package com.werfen.laboratory.features.sample.controller.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class SampleResponseDTO {

    private UUID id;

    private String barcode;

    private PatientResponseDTO patient;

    private List<TestResultResponseDTO> testOrders = new ArrayList<>();

}