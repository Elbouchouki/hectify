package org.elbouchouki.hectify.core.dto.permission;

import jakarta.validation.constraints.NotBlank;

public record PermissionCreateRequest(
        @NotBlank
        String permissionName,
        Boolean active
) {
}
