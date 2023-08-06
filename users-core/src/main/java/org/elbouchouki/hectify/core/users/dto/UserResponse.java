package org.elbouchouki.hectify.core.users.dto;

import lombok.Builder;
import org.elbouchouki.hectify.core.users.entitie.Sexe;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

public record UserResponse(
        String userId,
        String username,
        String email,
        String firstName,
        String lastName,
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
