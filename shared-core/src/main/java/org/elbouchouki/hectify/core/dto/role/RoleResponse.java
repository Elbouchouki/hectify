package org.elbouchouki.hectify.core.dto.role;


import org.elbouchouki.hectify.core.dto.permission.PermissionResponse;

import java.sql.Timestamp;
import java.util.Set;

public record RoleResponse(
        Long roleId,
        String roleName,
        Boolean active,
        Timestamp createdAt,
        Timestamp updatedAt,
        Set<PermissionResponse> permissions
) {
}
