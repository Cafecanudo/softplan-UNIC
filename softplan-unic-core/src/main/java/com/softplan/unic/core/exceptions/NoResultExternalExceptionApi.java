package com.softplan.unic.core.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoResultExternalExceptionApi extends NoResultExceptionApi {

    public NoResultExternalExceptionApi() {
        this.externalService = true;
    }

    public NoResultExternalExceptionApi(String msg) {
        super(msg);
        this.externalService = true;
    }

    public NoResultExternalExceptionApi(String message, Object... params) {
        super(message, params);
        this.externalService = true;
    }

    public static NoResultExternalExceptionApi extractJSON(String json) {
        try {
            ApiErrorResponse api = new ObjectMapper().readValue(json, ApiErrorResponse.class);
            return new NoResultExternalExceptionApi(api.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
