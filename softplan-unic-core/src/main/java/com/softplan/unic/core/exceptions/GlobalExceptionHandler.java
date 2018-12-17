package com.softplan.unic.core.exceptions;

import com.netflix.client.ClientException;
import feign.RetryableException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {RetryableException.class, ClientException.class})
    @ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
    public ApiErrorResponse clientExternalException(Exception except) {
        return ApiErrorResponse.builder().code(HttpStatus.FAILED_DEPENDENCY.value()).status(HttpStatus.FAILED_DEPENDENCY)
                .message("External service is not available")
                .cause(ExceptionUtils.getRootCauseMessage(except))
                .build();
    }

    @ExceptionHandler(value = {NoResultExceptionApi.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse noFoundException(NoResultExceptionApi except) {
        return ApiErrorResponse.builder().code(HttpStatus.NOT_FOUND.ordinal()).status(HttpStatus.NOT_FOUND).message(
                Optional.of(except.getMessage()).orElse(except.toString()))
                .cause(ExceptionUtils.getRootCauseMessage(except))
                .build();
    }

}
