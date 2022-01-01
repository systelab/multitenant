package com.werfen.inventory.features.product.controller.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class ProductRequestDTO {

    @Size(max = 255)
    private String name;

}