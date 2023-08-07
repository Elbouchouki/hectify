package org.elbouchouki.hectify.core.exception;


import lombok.Getter;

@Getter
public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException() {
    }

    public InvalidPasswordException(String message) {
        super(message);
    }

    public InvalidPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPasswordException(Throwable cause) {
        super(cause);
    }
}
