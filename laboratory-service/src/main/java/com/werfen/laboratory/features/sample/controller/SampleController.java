package com.werfen.laboratory.features.sample.controller;

import com.werfen.laboratory.features.sample.controller.dto.SampleMapper;
import com.werfen.laboratory.features.sample.controller.dto.SampleRequestDTO;
import com.werfen.laboratory.features.sample.controller.dto.SampleResponseDTO;
import com.werfen.laboratory.features.sample.model.Sample;
import com.werfen.laboratory.features.sample.service.SampleService;
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

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@Tag(name = "Sample")
@RequiredArgsConstructor
@RestController()
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class SampleController {

    private final SampleService sampleService;
    private final SampleMapper sampleMapper;


    @Operation(description = "Get all Samples")
    @PageableAsQueryParam
    @SecurityRequirement(name = "Authorization")
    @GetMapping("samples")
    public ResponseEntity<Page<SampleResponseDTO>> getAll(@Parameter(hidden = true) Pageable pageable) {
        return ResponseEntity.ok(this.sampleService.getByFilter(pageable).map(sampleMapper::toResponseDTO));
    }

    @Operation(description = "Get a Sample")
    @SecurityRequirement(name = "Authorization")
    @GetMapping("samples/{id}")
    public ResponseEntity<SampleResponseDTO> get(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(sampleMapper.toResponseDTO(this.sampleService.getSample(id)));
    }

    @Operation(description = "Create an Sample")
    @SecurityRequirement(name = "Authorization")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @PostMapping("samples/sample")
    public ResponseEntity<SampleResponseDTO> create(@RequestBody @Parameter(description = "Sample", required = true) @Valid SampleRequestDTO dto) {
        Sample createdSample = this.sampleService.createSample(dto.getBarcode(), dto.getPatientId(), dto.getTests());
        URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/samples/{id}").buildAndExpand(createdSample.getId()).toUri();
        return ResponseEntity.created(uri).body(sampleMapper.toResponseDTO(createdSample));
    }

    @Operation(description = "Delete a Sample")
    @SecurityRequirement(name = "Authorization")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @DeleteMapping("samples/{id}")
    public ResponseEntity remove(@PathVariable("id") UUID id) {
        this.sampleService.removeSample(id);
        return ResponseEntity.noContent().build();
    }
}