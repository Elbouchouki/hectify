package org.elbouchouki.hectify.rest.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.elbouchouki.hectify.core.dto.role.RoleCreateRequest;
import org.elbouchouki.hectify.core.dto.role.RoleResponse;
import org.elbouchouki.hectify.core.dto.role.RoleUpdateRequest;
import org.elbouchouki.hectify.core.dto.shared.PagingResponse;
import org.elbouchouki.hectify.core.users.service.RoleService;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/roles")
@Validated
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<PagingResponse<RoleResponse>> getAllRoles(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Range(min = 1, max = 100) int size
    ) {
        return ResponseEntity
                .ok()
                .body(
                        roleService.getRoles(page, size)
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResponse> getRoleById(
            @NotNull @PathVariable("id") Long id
    ) {
        return ResponseEntity
                .ok()
                .body(
                        roleService.getRole(id)
                );
    }

    @PostMapping
    public ResponseEntity<RoleResponse> createRole(
            @Valid @RequestBody RoleCreateRequest userCreateRequest
    ) {
        return ResponseEntity
                .ok()
                .body(
                        roleService.createRole(userCreateRequest)
                );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RoleResponse> updateRole(
            @NotNull @PathVariable("id") Long id,
            @Valid @RequestBody RoleUpdateRequest request
    ) {
        return ResponseEntity
                .ok()
                .body(
                        roleService.updateRole(id, request)
                );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(
            @NotNull @PathVariable("id") Long id
    ) {
        roleService.deleteRole(id);
        return ResponseEntity
                .noContent()
                .build();
    }

}
