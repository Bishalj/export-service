package com.bishal.bulk.export.common.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public abstract class AbstractBaseResponse{

    private String message;

    public void setMessage(String message) {
        this.message = message;
    }
}
