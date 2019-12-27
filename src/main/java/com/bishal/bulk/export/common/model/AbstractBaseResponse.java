package com.bishal.bulk.export.common.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public abstract class AbstractBaseResponse{

    private String message;

    private HttpStatus status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
