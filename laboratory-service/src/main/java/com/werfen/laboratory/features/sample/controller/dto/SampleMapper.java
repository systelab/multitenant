package com.werfen.laboratory.features.sample.controller.dto;

import com.werfen.laboratory.features.sample.model.Sample;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SampleMapper {

    SampleResponseDTO toResponseDTO(Sample sample);
}
