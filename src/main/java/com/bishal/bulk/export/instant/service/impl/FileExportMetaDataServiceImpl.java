package com.bishal.bulk.export.instant.service.impl;

import com.bishal.bulk.export.common.mapper.response.FileMetaDetailsResponse;
import com.bishal.bulk.export.common.mapper.resquest.DataExportRequestMapper;
import com.bishal.bulk.export.common.utils.database.DatabaseConnectionStoreUtils;
import com.bishal.bulk.export.database.model.DatabaseCredentials;
import com.bishal.bulk.export.database.service.IDatabaseCredentialService;
import com.bishal.bulk.export.database.service.impl.DatabaseCredentialServiceImpl;
import com.bishal.bulk.export.database.utils.DatabaseCredentialUtils;
import com.bishal.bulk.export.instant.service.IFileExportMetaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class FileExportMetaDataServiceImpl implements IFileExportMetaDataService {

    @Autowired
    private DatabaseCredentialUtils databaseCredentialUtils;

    @Autowired
    private IDatabaseCredentialService databaseCredentialService;

    @Override
    public Flux<FileMetaDetailsResponse> getDetailOfFileContainingData(final DataExportRequestMapper dataExportRequestMapper) {
        if(isRequestDataInvalid(dataExportRequestMapper))
            return Flux.error(new IllegalArgumentException("Invalid requestData"));
        return databaseCredentialService
                .getDatabaseCredentialDetails(dataExportRequestMapper)
                .flatMap( aDatabaseCredential -> DatabaseConnectionStoreUtils.getDatabaseConnectionFromDatabaseStore(aDatabaseCredential, dataExportRequestMapper.getDatabaseUniqueKey()))
                .flatMapMany(reactiveMongoTemplate -> Flux.empty());
    }

    private boolean isRequestDataInvalid(DataExportRequestMapper dataExportRequestMapper) {
        return  ObjectUtils.isEmpty(dataExportRequestMapper) ||
                StringUtils.isEmpty(dataExportRequestMapper.getDatabaseQuery()) ||
                ObjectUtils.isEmpty(dataExportRequestMapper.getBatchSizePerFile()) ||
                StringUtils.isEmpty(dataExportRequestMapper.getDatabaseUniqueKey()) ||
                ObjectUtils.isEmpty(dataExportRequestMapper.getIsDataNeedsToBeOrdered());
    }

}
