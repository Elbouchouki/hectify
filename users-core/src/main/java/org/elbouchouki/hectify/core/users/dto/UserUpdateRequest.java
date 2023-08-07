package org.elbouchouki.hectify.core.users.dto;

import jakarta.validation.constraints.NotBlank;
import org.elbouchouki.hectify.core.users.entitie.Sexe;

public record UserUpdateRequest(
        String firstname,
        String lastname,
        Sexe sexe
) {
}
