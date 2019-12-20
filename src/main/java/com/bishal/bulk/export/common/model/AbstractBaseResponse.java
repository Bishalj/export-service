package com.bishal.bulk.export.common.model;

import org.springframework.http.HttpStatus;

public abstract class AbstractBaseResponse{

    private String message;

    private HttpStatus status;

}
