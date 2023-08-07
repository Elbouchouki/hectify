package org.elbouchouki.hectify.core.dto.role;

import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record RoleCreateRequest(
        @NotBlank
        String roleName,
        Boolean active,
        Set<Long> permissions
) {
}
