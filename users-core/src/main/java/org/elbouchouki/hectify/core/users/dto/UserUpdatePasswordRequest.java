package org.elbouchouki.hectify.core.users.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdatePasswordRequest(
        @NotBlank @Size(min = 8) String newPassword,
        @NotBlank String oldPassword
) {
}
