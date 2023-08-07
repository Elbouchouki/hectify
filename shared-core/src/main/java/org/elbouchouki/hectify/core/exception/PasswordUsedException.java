package org.elbouchouki.hectify.core.exception;


import lombok.Getter;

import java.util.List;

@Getter
public class PasswordUsedException extends RuntimeException {

    public PasswordUsedException() {
    }

    public PasswordUsedException(String message) {
        super(message);
    }

    public PasswordUsedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordUsedException(Throwable cause) {
        super(cause);
    }
}
