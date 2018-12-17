package com.softplan.unic.core.exceptions;

import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import org.springframework.http.HttpStatus;

@Data
@Builder
@Generated
public class ApiErrorResponse {

    private HttpStatus status;
    private int code;
    private String message;
    private String cause;

}