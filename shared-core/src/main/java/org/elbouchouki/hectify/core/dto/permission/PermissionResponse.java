package org.elbouchouki.hectify.core.dto.permission;

import java.sql.Timestamp;

public record PermissionResponse(
        Long permissionId,
        String permissionName,
        Boolean active,
        Timestamp createdAt,
        Timestamp updatedAt
) {
}
