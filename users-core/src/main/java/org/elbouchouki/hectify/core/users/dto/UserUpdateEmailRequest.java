package org.elbouchouki.hectify.core.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserUpdateEmailRequest(
        @Email @NotBlank String email,
        @NotBlank String currentPassword
) {
}
