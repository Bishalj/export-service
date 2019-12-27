package com.bishal.bulk.export.instant.service;

import com.bishal.bulk.export.common.mapper.response.FileDetailsResponseMapper;
import com.bishal.bulk.export.common.mapper.resquest.DataExportRequestMapper;
import reactor.core.publisher.Flux;

public interface IFileExportMetaDataService {

    Flux<FileDetailsResponseMapper> getDetailOfFileContainingData(final DataExportRequestMapper dataExportRequestMapper);

}
