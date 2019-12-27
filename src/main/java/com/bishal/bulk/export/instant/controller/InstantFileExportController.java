package com.bishal.bulk.export.instant.controller;

import com.bishal.bulk.export.common.constants.ApiRouteUrl;
import com.bishal.bulk.export.common.mapper.response.FileDetailsResponseMapper;
import com.bishal.bulk.export.common.mapper.resquest.DataExportRequestMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class InstantFileExportController {


    @PostMapping(value = ApiRouteUrl.InstantController.EXPORT_DATA)
    public ResponseEntity<Flux<FileDetailsResponseMapper>> getFileExportDetail(@RequestBody final DataExportRequestMapper dataExportRequestMapper){
        FileDetailsResponseMapper fileDetailsResponseMapper = new FileDetailsResponseMapper();
        fileDetailsResponseMapper.setStatus(HttpStatus.NOT_FOUND);
        fileDetailsResponseMapper.setMessage("No content found");
        return new ResponseEntity<>(Flux.just(fileDetailsResponseMapper), HttpStatus.NOT_FOUND);
    }
}
