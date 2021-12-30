package com.werfen.laboratory.features.test.controller.dto;

import com.werfen.laboratory.features.test.model.Test;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TestMapper {

    TestResponseDTO toResponseDTO(Test test);

    Test fromRequestDTO(TestRequestDTO dto);

}
