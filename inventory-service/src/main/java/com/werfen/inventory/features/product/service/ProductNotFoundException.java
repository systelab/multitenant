package com.werfen.inventory.features.product.service;

import com.werfen.inventory.core.handlers.exception.ApiExceptionCode;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@ApiExceptionCode(value = "ERR_PRODUCT_NOT_FOUND", text = "Product not found")
public class ProductNotFoundException extends RuntimeException {

    private final String id;

    public ProductNotFoundException(Long id) {
        super("product-not-found-" + id.toString());
        this.id = id.toString();
    }

    public String getProductId() {
        return id;
    }
}