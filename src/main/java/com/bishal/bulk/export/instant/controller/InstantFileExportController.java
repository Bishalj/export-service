package com.bishal.bulk.export.instant.controller;

import com.bishal.bulk.export.common.constants.ApiRouteUrl;
import com.bishal.bulk.export.common.constants.HttpCodesConstant;
import com.bishal.bulk.export.common.exception.NoDataFoundException;
import com.bishal.bulk.export.common.mapper.response.FileMetaDetailsResponse;
import com.bishal.bulk.export.common.mapper.resquest.DataExportRequestMapper;
import com.bishal.bulk.export.common.service.IExportServiceBeanFactory;
import com.bishal.bulk.export.common.utils.ResponseEntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class InstantFileExportController {

    private final Logger INSTANT_FILE_EXPORT_LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IExportServiceBeanFactory exportServiceBeanFactory;


    @PostMapping(value = ApiRouteUrl.InstantController.EXPORT_DATA)
    public ResponseEntity<Flux<FileMetaDetailsResponse>> getFileExportDetail(@RequestBody final DataExportRequestMapper dataExportRequestMapper){
        Flux<FileMetaDetailsResponse> fileDetailFlux = exportServiceBeanFactory
                .getInstantExportBeanFactory()
                .getFileExportMetaDataService()
                .getDetailOfFileContainingData(dataExportRequestMapper)
                .switchIfEmpty(Flux.error(new NoDataFoundException("No Data Found")));

        return ResponseEntityUtils.getResponseFromFlux(fileDetailFlux, HttpCodesConstant.HTTP_SUCCESS_STATUS);

    }
}
