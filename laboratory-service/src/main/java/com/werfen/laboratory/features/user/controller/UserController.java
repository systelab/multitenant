package com.werfen.laboratory.features.user.controller;

import com.werfen.laboratory.features.user.controller.dto.UserMapper;
import com.werfen.laboratory.features.user.controller.dto.UserRequestDTO;
import com.werfen.laboratory.features.user.controller.dto.UserResponseDTO;
import com.werfen.laboratory.features.user.model.User;
import com.werfen.laboratory.features.user.service.UserService;
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

@Tag(name = "User")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Operation(description = "Get all Users")
    @SecurityRequirement(name = "Authorization")
    @PageableAsQueryParam
    @GetMapping("users")
    public ResponseEntity<Page<UserResponseDTO>> getAllUsers(@Parameter(hidden = true) Pageable pageable) {
        return ResponseEntity.ok(this.userService.getAllUsers(pageable).map(userMapper::toResponseDTO));
    }

    @Operation(description = "Get User")
    @SecurityRequirement(name = "Authorization")
    @GetMapping("users/{id}")
    public ResponseEntity<UserResponseDTO> getUsers(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(userMapper.toResponseDTO(this.userService.getUser(id)));
    }

    @Operation(description = "Create a User")
    @SecurityRequirement(name = "Authorization")
    @PostMapping("users/user")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Parameter(description = "User", required = true) @Valid UserRequestDTO dto) {
        User user = this.userService.createUser(userMapper.fromRequestDTO(dto));
        URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(userMapper.toResponseDTO(user));
    }

    @Operation(description = "Update a User")
    @SecurityRequirement(name = "Authorization")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("users/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable("id") UUID id, @Valid @RequestBody @Parameter(description = "User", required = true) UserRequestDTO dto) {
        User user = this.userService.updateUser(id, userMapper.fromRequestDTO(dto));
        return ResponseEntity.ok(userMapper.toResponseDTO(user));
    }

    @Operation(description = "Delete a User")
    @SecurityRequirement(name = "Authorization")
    @DeleteMapping("users/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity deleteUser(@PathVariable("id") UUID id) {
        this.userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}