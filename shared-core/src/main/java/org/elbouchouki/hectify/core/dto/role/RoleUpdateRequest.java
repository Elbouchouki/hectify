package org.elbouchouki.hectify.core.dto.role;

import java.util.Set;

public record RoleUpdateRequest(
        String roleName,
        Boolean active,
        Set<Long> permissions
) {
}
