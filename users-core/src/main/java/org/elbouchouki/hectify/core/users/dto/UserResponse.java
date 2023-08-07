package org.elbouchouki.hectify.core.users.dto;

import org.elbouchouki.hectify.core.users.entitie.Sexe;

import java.sql.Timestamp;

public record UserResponse(
        String userId,
        String username,
        String email,
        String firstname,
        String lastname,
        Sexe sexe,
        String picture,

        Boolean confirmed,
        Boolean active,
        Boolean locked,
        Boolean expired,


        Timestamp createdAt,
        Timestamp updatedAt
) {
}
