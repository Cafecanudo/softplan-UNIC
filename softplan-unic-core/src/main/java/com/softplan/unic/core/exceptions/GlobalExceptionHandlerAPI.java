package com.softplan.unic.core.exceptions;

import com.mongodb.MongoWriteException;
import com.netflix.client.ClientException;
import feign.RetryableException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

public abstract class GlobalExceptionHandlerAPI {

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

    @ExceptionHandler(value = {MongoWriteException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse duplicateException(MongoWriteException except) {
        HttpStatus status = except.getError().getMessage().indexOf("duplicate") > -1 ? HttpStatus.CONFLICT : HttpStatus.BAD_REQUEST;
        return ApiErrorResponse.builder().code(status.ordinal()).status(status).message(
                Optional.of(except.getMessage()).orElse(except.toString()))
                .cause(ExceptionUtils.getRootCauseMessage(except))
                .build();
    }

}
