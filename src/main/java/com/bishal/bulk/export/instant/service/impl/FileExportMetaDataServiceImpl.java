package com.bishal.bulk.export.instant.service.impl;

import com.bishal.bulk.export.common.mapper.response.FileMetaDetailsResponse;
import com.bishal.bulk.export.common.mapper.resquest.DataExportRequestMapper;
import com.bishal.bulk.export.instant.service.IFileExportMetaDataService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;

@Service
public class FileExportMetaDataServiceImpl implements IFileExportMetaDataService {
    @Override
    public Flux<FileMetaDetailsResponse> getDetailOfFileContainingData(final DataExportRequestMapper dataExportRequestMapper) {
        if(isRequestDataInvalid(dataExportRequestMapper))
            return Flux.error(IllegalArgumentException::new);

        return Flux.empty();
    }

    private boolean isRequestDataInvalid(DataExportRequestMapper dataExportRequestMapper) {
        return  ObjectUtils.isEmpty(dataExportRequestMapper) ||
                StringUtils.isEmpty(dataExportRequestMapper.getDatabaseQuery()) ||
                ObjectUtils.isEmpty(dataExportRequestMapper.getBatchSizePerFile()) ||
                StringUtils.isEmpty(dataExportRequestMapper.getDatabaseUniqueKey()) ||
                ObjectUtils.isEmpty(dataExportRequestMapper.getDataNeedsToBeOrdered());
    }
}
