package com.bishal.bulk.export.instant.service.impl;

import com.bishal.bulk.export.common.mapper.response.FileDetailsResponseMapper;
import com.bishal.bulk.export.common.mapper.resquest.DataExportRequestMapper;
import com.bishal.bulk.export.instant.service.IFileExportMetaDataService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class FileExportMetaDataServiceImpl implements IFileExportMetaDataService {
    @Override
    public Flux<FileDetailsResponseMapper> getFilesMetaDetailsContainingData(DataExportRequestMapper dataExportRequestMapper) {
        return null;
    }
}