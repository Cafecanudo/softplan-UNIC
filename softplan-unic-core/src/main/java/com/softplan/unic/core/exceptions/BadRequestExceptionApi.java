package com.softplan.unic.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestExceptionApi extends RestClientException {

    public BadRequestExceptionApi(String msg) {
        super(msg);
    }

    public BadRequestExceptionApi(String statusText, Object... params) {
        super(String.format(statusText, params));
    }

}
