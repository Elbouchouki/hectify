package org.elbouchouki.hectify.rest.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.elbouchouki.hectify.core.dto.shared.PagingResponse;
import org.elbouchouki.hectify.core.dto.user.*;
import org.elbouchouki.hectify.core.users.service.UserService;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<PagingResponse<UserResponse>> getAllUsers(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Range(min = 1, max = 100) int size
    ) {
        return ResponseEntity
                .ok()
                .body(
                        userService.getAllUsers(page, size)
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(
            @NotBlank @PathVariable("id") String id
    ) {
        return ResponseEntity
                .ok()
                .body(
                        userService.getUserById(id)
                );
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(
            @Valid @RequestBody UserCreateRequest userCreateRequest
    ) {
        return ResponseEntity
                .ok()
                .body(
                        userService.createUser(userCreateRequest)
                );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @NotBlank @PathVariable("id") String id,
            @Valid @RequestBody UserUpdateRequest request
    ) {
        return ResponseEntity
                .ok()
                .body(
                        userService.updateUser(id, request)
                );
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<UserResponse> updatePassword(
            @NotBlank @PathVariable("id") String id,
            @Valid @RequestBody UserUpdatePasswordRequest request
    ) {
        return ResponseEntity
                .ok()
                .body(
                        userService.updatePassword(id, request)
                );
    }

    @PatchMapping("/{id}/email")
    public ResponseEntity<UserResponse> updateEmail(
            @NotBlank @PathVariable("id") String id,
            @Valid @RequestBody UserUpdateEmailRequest request
    ) {
        return ResponseEntity
                .ok()
                .body(
                        userService.updateEmail(id, request)
                );
    }

    @PatchMapping("/{id}/username")
    public ResponseEntity<UserResponse> updateUsername(
            @NotBlank @PathVariable("id") String id,
            @Valid @RequestBody UserUpdateUsernameRequest request
    ) {
        return ResponseEntity
                .ok()
                .body(
                        userService.updateUsername(id, request)
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @NotBlank @PathVariable("id") String id
    ) {
        userService.deleteUser(id);
        return ResponseEntity
                .noContent()
                .build();
    }

}
