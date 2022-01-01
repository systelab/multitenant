package com.werfen.inventory.features.product.controller.dto;

import com.werfen.inventory.features.product.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponseDTO toResponseDTO(Product product);

    Product fromRequestDTO(ProductRequestDTO dto);

}
