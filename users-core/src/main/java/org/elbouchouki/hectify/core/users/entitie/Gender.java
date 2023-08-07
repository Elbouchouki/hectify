package org.elbouchouki.hectify.core.users.entitie;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
    M("MALE"),
    F("FEMALE"),
    U("UNKNOWN");
    private final String value;
}
