package com.bishal.bulk.export.instant.service.impl;

import com.bishal.bulk.export.common.mapper.response.FileMetaDetailsResponse;
import com.bishal.bulk.export.common.mapper.resquest.DataExportRequestMapper;
import com.bishal.bulk.export.common.service.IExportServiceBeanFactory;
import com.bishal.bulk.export.common.utils.database.DatabaseConnectionStoreUtils;
import com.bishal.bulk.export.database.service.IDatabaseCredentialService;
import com.bishal.bulk.export.database.utils.DatabaseCredentialUtils;
import com.bishal.bulk.export.instant.service.IFileExportMetaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;


@Service
public class FileExportMetaDataServiceImpl implements IFileExportMetaDataService {

    @Autowired
    private DatabaseCredentialUtils databaseCredentialUtils;

    @Autowired
    private IExportServiceBeanFactory exportServiceBeanFactory;

    @Override
    public Flux<FileMetaDetailsResponse> getDetailOfFileContainingData(final DataExportRequestMapper dataExportRequestMapper) {
        if(isRequestDataInvalid(dataExportRequestMapper))
            return Flux.error(new IllegalArgumentException("Invalid requestData"));
        return exportServiceBeanFactory
                .getDatabaseBeanFactory()
                .getDatabaseCredentialService()
                .getDatabaseCredentialDetails(dataExportRequestMapper)
                .map( aDatabaseCredential -> DatabaseConnectionStoreUtils.getDatabaseConnectionFromDatabaseStore(aDatabaseCredential, dataExportRequestMapper.getDatabaseUniqueKey()))
                .flatMapMany(reactiveMongoTemplate -> Flux.empty());
    }

    private boolean isRequestDataInvalid(DataExportRequestMapper dataExportRequestMapper) {
        return  ObjectUtils.isEmpty(dataExportRequestMapper) ||
                StringUtils.isEmpty(dataExportRequestMapper.getDatabaseQuery()) ||
                ObjectUtils.isEmpty(dataExportRequestMapper.getBatchSizePerFile()) ||
                StringUtils.isEmpty(dataExportRequestMapper.getDatabaseUniqueKey()) ||
                ObjectUtils.isEmpty(dataExportRequestMapper.getIsDataNeedsToBeOrdered()) ||
                ObjectUtils.isEmpty(dataExportRequestMapper.getDatabaseName());
    }

}
