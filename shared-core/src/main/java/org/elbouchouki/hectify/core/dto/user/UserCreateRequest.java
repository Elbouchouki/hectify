package org.elbouchouki.hectify.core.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record UserCreateRequest(
        @Size(min = 8)
        @NotBlank
        String username,
        @Email
        @NotBlank
        String email,
        @NotBlank
        String firstname,
        @NotBlank
        String lastname,
        @Size(min = 8)
        String password,
        String gender,
        Set<Long> roles
) {
}
