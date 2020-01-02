package com.bishal.bulk.export.common.constants;

import org.springframework.http.HttpStatus;

public class HttpCodesConstant {

    private HttpCodesConstant() {
    }

    public static final HttpStatus HTTP_SUCCESS_STATUS = HttpStatus.ACCEPTED;

    public static final HttpStatus HTTP_NO_DATA_FOUND_STATUS = HttpStatus.NOT_FOUND;

    public static final HttpStatus HTTP_INTERNAL_SERVER_ERROR_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

    public static final HttpStatus HTTP_BAD_REQUEST = HttpStatus.BAD_REQUEST;
}
