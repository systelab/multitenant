package com.werfen.laboratory.features.sample.controller.dto;

import lombok.Data;

@Data
public class TestResultResponseDTO {
    private TestResponseDTO test;
    private String result;
}
