package org.elbouchouki.hectify.core.exception;


import lombok.Getter;

import java.util.List;

@Getter
public class AlreadyExistsException extends BusinessException {
    private final List<String> identifiers;

    public AlreadyExistsException(String key, Object[] args, List<String> identifiers) {
        super(key, args);
        this.identifiers = identifiers;
    }
}
