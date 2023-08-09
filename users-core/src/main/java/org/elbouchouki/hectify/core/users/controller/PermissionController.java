package org.elbouchouki.hectify.core.users.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.elbouchouki.hectify.core.dto.permission.PermissionCreateRequest;
import org.elbouchouki.hectify.core.dto.permission.PermissionResponse;
import org.elbouchouki.hectify.core.dto.permission.PermissionUpdateRequest;
import org.elbouchouki.hectify.core.dto.shared.PagingResponse;
import org.elbouchouki.hectify.core.users.service.PermissionService;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/core/permissions")
@Validated
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    @GetMapping
    public ResponseEntity<PagingResponse<PermissionResponse>> getAllPermissions(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Range(min = 1, max = 100) int size
    ) {
        return ResponseEntity
                .ok()
                .body(
                        permissionService.getPermissions(page, size)
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionResponse> getPermissionById(
            @NotNull @PathVariable("id") Long id
    ) {
        return ResponseEntity
                .ok()
                .body(
                        permissionService.getPermission(id)
                );
    }

    @PostMapping
    public ResponseEntity<PermissionResponse> createPermission(
            @Valid @RequestBody PermissionCreateRequest userCreateRequest
    ) {
        return ResponseEntity
                .ok()
                .body(
                        permissionService.createPermission(userCreateRequest)
                );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PermissionResponse> updatePermission(
            @NotNull @PathVariable("id") Long id,
            @Valid @RequestBody PermissionUpdateRequest request
    ) {
        return ResponseEntity
                .ok()
                .body(
                        permissionService.updatePermission(id, request)
                );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePermission(
            @NotNull @PathVariable("id") Long id
    ) {
        permissionService.deletePermission(id);
        return ResponseEntity
                .noContent()
                .build();
    }

}
