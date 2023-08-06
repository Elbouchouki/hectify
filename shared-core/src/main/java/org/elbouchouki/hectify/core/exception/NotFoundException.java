package org.elbouchouki.hectify.core.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class NotFoundException extends BusinessException {
    private final List<String> identifiers;

    public NotFoundException(String key, Object[] args, List<String> identifiers) {
        super(key, args);
        this.identifiers = identifiers;
    }
}
