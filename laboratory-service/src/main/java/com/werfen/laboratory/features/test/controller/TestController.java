package com.werfen.laboratory.features.test.controller;

import com.werfen.laboratory.features.test.controller.dto.TestMapper;
import com.werfen.laboratory.features.test.controller.dto.TestRequestDTO;
import com.werfen.laboratory.features.test.controller.dto.TestResponseDTO;
import com.werfen.laboratory.features.test.model.Test;
import com.werfen.laboratory.features.test.service.TestService;
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
import java.util.UUID;

@Tag(name = "Test")
@RequiredArgsConstructor
@RestController()
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class TestController {

    private final TestService testService;
    private final TestMapper testMapper;


    @Operation(description = "Get all Tests")
    @PageableAsQueryParam
    @SecurityRequirement(name = "Authorization")
    @GetMapping("tests")
    public ResponseEntity<Page<TestResponseDTO>> getAll(@Parameter(hidden = true) Pageable pageable, @RequestParam(required = false) String search) {
        return ResponseEntity.ok(this.testService.getByFilter(pageable, search).map(testMapper::toResponseDTO));
    }

    @Operation(description = "Get an Test")
    @SecurityRequirement(name = "Authorization")
    @GetMapping("tests/{id}")
    public ResponseEntity<TestResponseDTO> get(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(testMapper.toResponseDTO(this.testService.getTest(id)));
    }

    @Operation(description = "Create an Test")
    @SecurityRequirement(name = "Authorization")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @PostMapping("tests/test")
    public ResponseEntity<TestResponseDTO> create(@RequestBody @Parameter(description = "Test", required = true) @Valid TestRequestDTO dto) {
        Test createdTest = this.testService.createTest(testMapper.fromRequestDTO(dto));
        URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/tests/{id}").buildAndExpand(createdTest.getId()).toUri();
        return ResponseEntity.created(uri).body(testMapper.toResponseDTO(createdTest));
    }

    @Operation(description = "Create or Update (idempotent) an existing Test")
    @SecurityRequirement(name = "Authorization")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @PutMapping("tests/{id}")
    public ResponseEntity<TestResponseDTO> update(@PathVariable("id") UUID id, @RequestBody @Parameter(description = "Test", required = true) @Valid TestRequestDTO dto) {
        Test updatedTest = this.testService.updateTest(id, testMapper.fromRequestDTO(dto));
        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.created(selfLink).body(testMapper.toResponseDTO(updatedTest));
    }

    @Operation(description = "Delete an Test")
    @SecurityRequirement(name = "Authorization")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @DeleteMapping("tests/{id}")
    public ResponseEntity remove(@PathVariable("id") UUID id) {
        this.testService.removeTest(id);
        return ResponseEntity.noContent().build();
    }
}