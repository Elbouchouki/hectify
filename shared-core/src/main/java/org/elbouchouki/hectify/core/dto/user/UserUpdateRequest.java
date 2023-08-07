package org.elbouchouki.hectify.core.dto.user;

import java.util.Set;

public record UserUpdateRequest(
        String firstname,
        String lastname,
        String gender,
        Set<Long> roles
) {
}
