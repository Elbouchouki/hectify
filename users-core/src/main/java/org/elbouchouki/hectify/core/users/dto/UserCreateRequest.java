package org.elbouchouki.hectify.core.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.elbouchouki.hectify.core.users.entitie.Sexe;

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
        Sexe sexe
) {
}
