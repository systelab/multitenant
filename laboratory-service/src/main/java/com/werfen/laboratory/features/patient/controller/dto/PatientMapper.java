package com.werfen.laboratory.features.patient.controller.dto;

import com.werfen.laboratory.features.patient.model.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    PatientResponseDTO toResponseDTO(Patient patient);

    Patient fromRequestDTO(PatientRequestDTO dto);

}
