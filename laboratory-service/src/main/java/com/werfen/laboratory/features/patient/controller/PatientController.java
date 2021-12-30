package com.werfen.laboratory.features.patient.controller;

import com.werfen.laboratory.features.patient.controller.dto.PatientMapper;
import com.werfen.laboratory.features.patient.controller.dto.PatientRequestDTO;
import com.werfen.laboratory.features.patient.controller.dto.PatientResponseDTO;
import com.werfen.laboratory.features.patient.model.Patient;
import com.werfen.laboratory.features.patient.service.PatientService;
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

@Tag(name = "Patient")
@RequiredArgsConstructor
@RestController()
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientController {

    private final PatientService patientService;
    private final PatientMapper patientMapper;


    @Operation(description = "Get all Patients")
    @PageableAsQueryParam
    @SecurityRequirement(name = "Authorization")
    @GetMapping("patients")
    public ResponseEntity<Page<PatientResponseDTO>> getAll(@Parameter(hidden = true) Pageable pageable) {
        return ResponseEntity.ok(this.patientService.getByFilter(pageable).map(patientMapper::toResponseDTO));
    }

    @Operation(description = "Get a Patient")
    @SecurityRequirement(name = "Authorization")
    @GetMapping("patients/{id}")
    public ResponseEntity<PatientResponseDTO> get(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(patientMapper.toResponseDTO(this.patientService.getPatient(id)));
    }

    @Operation(description = "Create an Patient")
    @SecurityRequirement(name = "Authorization")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @PostMapping("patients/patient")
    public ResponseEntity<PatientResponseDTO> create(@RequestBody @Parameter(description = "Patient", required = true) @Valid PatientRequestDTO dto) {
        Patient createdPatient = this.patientService.createPatient(patientMapper.fromRequestDTO(dto));
        URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/patients/{id}").buildAndExpand(createdPatient.getId()).toUri();
        return ResponseEntity.created(uri).body(patientMapper.toResponseDTO(createdPatient));
    }

    @Operation(description = "Create or Update (idempotent) an existing Patient")
    @SecurityRequirement(name = "Authorization")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @PutMapping("patients/{id}")
    public ResponseEntity<PatientResponseDTO> update(@PathVariable("id") UUID id, @RequestBody @Parameter(description = "Patient", required = true) @Valid PatientRequestDTO dto) {
        Patient updatedPatient = this.patientService.updatePatient(id, patientMapper.fromRequestDTO(dto));
        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.created(selfLink).body(patientMapper.toResponseDTO(updatedPatient));
    }

    @Operation(description = "Delete an Patient")
    @SecurityRequirement(name = "Authorization")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @DeleteMapping("patients/{id}")
    public ResponseEntity remove(@PathVariable("id") UUID id) {
        this.patientService.removePatient(id);
        return ResponseEntity.noContent().build();
    }
}