package com.softplan.unic.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestExceptionApi extends HttpClientErrorException {

    public BadRequestExceptionApi(String statusText) {
        super(HttpStatus.BAD_REQUEST, statusText);
    }

    public BadRequestExceptionApi(String statusText, Object... params) {
        super(HttpStatus.BAD_REQUEST, String.format(statusText, params));
    }
}
