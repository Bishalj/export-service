package com.bishal.bulk.export.common.controller;

import com.bishal.bulk.export.common.constants.HttpCodesConstant;
import com.bishal.bulk.export.common.exception.NoDataFoundException;
import com.bishal.bulk.export.common.mapper.response.BaseErrorResponse;
import com.bishal.bulk.export.common.utils.BaseErrorResponseUtils;
import com.bishal.bulk.export.common.utils.ResponseEntityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<BaseErrorResponse> noDataFoundException(final NoDataFoundException exception) {

        return ResponseEntityUtils
                .getResponse(
                        BaseErrorResponseUtils.getBaseErrorResponse(exception.getMessage()),
                        HttpCodesConstant.HTTP_NO_DATA_FOUND_STATUS
                );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BaseErrorResponse> illegalArgumentException(final IllegalArgumentException exception) {
        return ResponseEntityUtils
                .getResponse(
                        BaseErrorResponseUtils.getBaseErrorResponse(exception.getMessage()),
                        HttpCodesConstant.HTTP_BAD_REQUEST
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseErrorResponse> globalException(final Exception exception) {
        return ResponseEntityUtils
                .getResponse(
                        BaseErrorResponseUtils.getBaseErrorResponse(exception.getMessage()),
                        HttpCodesConstant.HTTP_INTERNAL_SERVER_ERROR_STATUS
                );
    }
}
