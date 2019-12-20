package com.bishal.bulk.export.instant.service;

import com.bishal.bulk.export.common.mapper.resquest.DataExportRequestMapper;
import reactor.core.publisher.Flux;

public interface IFileExportMetaDataService {

    Flux<DataExportRequestMapper> getFilesMetaDetailsContainingData(final DataExportRequestMapper dataExportRequestMapper);

}
