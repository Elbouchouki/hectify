package org.elbouchouki.hectify.core.users.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdateUsernameRequest(
        @Size(min = 8) @NotBlank String username,
        @NotBlank String currentPassword
) {
}
