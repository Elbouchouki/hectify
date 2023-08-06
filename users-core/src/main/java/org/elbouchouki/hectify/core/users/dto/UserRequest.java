package org.elbouchouki.hectify.core.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.elbouchouki.hectify.core.users.entitie.Sexe;

public record UserRequest(
        @NotBlank
        String username,
        @Email
        @NotBlank
        String email,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        Sexe sexe,
        String picture
) {
}
