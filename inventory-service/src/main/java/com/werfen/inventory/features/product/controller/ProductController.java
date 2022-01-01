package com.werfen.inventory.features.product.controller;

import com.werfen.inventory.features.product.controller.dto.ProductMapper;
import com.werfen.inventory.features.product.controller.dto.ProductRequestDTO;
import com.werfen.inventory.features.product.controller.dto.ProductResponseDTO;
import com.werfen.inventory.features.product.model.Product;
import com.werfen.inventory.features.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Tag(name = "Product")
@RequiredArgsConstructor
@RestController()
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;


    @Operation(description = "Get all Products")
    @PageableAsQueryParam
    @SecurityRequirement(name = "Authorization")
    @GetMapping("products")
    public ResponseEntity<Page<ProductResponseDTO>> getAll(@Parameter(hidden = true) Pageable pageable) {
        return ResponseEntity.ok(this.productService.getByFilter(pageable).map(productMapper::toResponseDTO));
    }

    @Operation(description = "Get a Product")
    @SecurityRequirement(name = "Authorization")
    @GetMapping("products/{id}")
    public ResponseEntity<ProductResponseDTO> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productMapper.toResponseDTO(this.productService.getProduct(id)));
    }

    @Operation(description = "Create an Product")
    @SecurityRequirement(name = "Authorization")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @PostMapping("products/product")
    public ResponseEntity<ProductResponseDTO> create(@RequestBody @Parameter(description = "Product", required = true) @Valid ProductRequestDTO dto) {
        Product createdProduct = this.productService.createProduct(productMapper.fromRequestDTO(dto));
        URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/products/{id}").buildAndExpand(createdProduct.getId()).toUri();
        return ResponseEntity.created(uri).body(productMapper.toResponseDTO(createdProduct));
    }

    @Operation(description = "Create or Update (idempotent) an existing Product")
    @SecurityRequirement(name = "Authorization")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @PutMapping("products/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable("id") Long id, @RequestBody @Parameter(description = "Product", required = true) @Valid ProductRequestDTO dto) {
        Product updatedProduct = this.productService.updateProduct(id, productMapper.fromRequestDTO(dto));
        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.created(selfLink).body(productMapper.toResponseDTO(updatedProduct));
    }

    @Operation(description = "Delete an Product")
    @SecurityRequirement(name = "Authorization")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @DeleteMapping("products/{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        this.productService.removeProduct(id);
        return ResponseEntity.noContent().build();
    }
}