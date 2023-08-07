package org.elbouchouki.hectify.core.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdatePasswordRequest(
        @NotBlank @Size(min = 8) String newPassword,
        @NotBlank String oldPassword
) {
}
