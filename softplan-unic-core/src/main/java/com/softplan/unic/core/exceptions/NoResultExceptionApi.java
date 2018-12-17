package com.softplan.unic.core.exceptions;

import com.mongodb.MongoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoResultExceptionApi extends MongoException {

    protected Boolean externalService;

    public NoResultExceptionApi() {
        super("Not found results.");
    }

    public NoResultExceptionApi(String msg) {
        super(msg);
    }

    public NoResultExceptionApi(String message, Object... params) {
        super(String.format(message, params));
    }
}
