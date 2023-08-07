package org.elbouchouki.hectify.core.dto.role;


import java.sql.Timestamp;

public record RoleResponse(
        Long roleId,
        String roleName,
        Boolean active,
        Timestamp createdAt,
        Timestamp updatedAt
) {
}
