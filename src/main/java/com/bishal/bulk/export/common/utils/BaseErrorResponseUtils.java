package com.bishal.bulk.export.common.utils;

import com.bishal.bulk.export.common.mapper.response.BaseErrorResponse;

public class BaseErrorResponseUtils {

    public static BaseErrorResponse getBaseErrorResponse(String message){
        BaseErrorResponse baseErrorResponse = new BaseErrorResponse();
        baseErrorResponse.setMessage(message);
        return baseErrorResponse;
    }
}
