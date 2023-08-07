package org.elbouchouki.hectify.core.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExceptionResponse {
    private int code;
    private String status;
    private String message;
    private List<String> identifiers;
    private List<ValidationError> errors;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();


    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class ValidationError {
        private String field;
        private String message;
    }
}
