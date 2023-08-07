package org.elbouchouki.hectify.core.dto.user;


import org.elbouchouki.hectify.core.dto.role.RoleResponse;

import java.sql.Timestamp;
import java.util.Set;

public record UserResponse(
        String userId,
        String username,
        String email,
        String firstname,
        String lastname,
        String gender,
        String picture,

        Boolean confirmed,
        Boolean active,
        Boolean locked,
        Boolean expired,

        Timestamp createdAt,
        Timestamp updatedAt,

        Set<RoleResponse> roles
) {
}
