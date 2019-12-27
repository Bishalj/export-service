package com.bishal.bulk.export.common.constants;

import org.springframework.http.HttpStatus;

public class HttpCodesConstant {
    public static HttpStatus HTTP_SUCCESS_STATUS = HttpStatus.ACCEPTED;

    public static HttpStatus HTTP_NO_DATA_FOUND_STATUS = HttpStatus.NOT_FOUND;

    public static HttpStatus HTTP_INTERNAL_SERVER_ERROR_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;
}
