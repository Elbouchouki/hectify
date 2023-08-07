package org.elbouchouki.hectify.core.dto.permission;

public record PermissionUpdateRequest(
        String permissionName,
        Boolean active
) {
}
