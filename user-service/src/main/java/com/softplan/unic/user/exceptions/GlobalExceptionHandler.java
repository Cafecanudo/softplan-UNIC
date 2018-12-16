package com.softplan.unic.user.exceptions;

import com.netflix.client.ClientException;
import feign.RetryableException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {RetryableException.class, ClientException.class})
    @ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
    public ApiErrorResponse noFound(Exception except) {
        return ApiErrorResponse.builder().code(HttpStatus.FAILED_DEPENDENCY.value()).status(HttpStatus.FAILED_DEPENDENCY)
                .message("External service is not available").cause(ExceptionUtils.getRootCauseMessage(except)).build();
    }

}
