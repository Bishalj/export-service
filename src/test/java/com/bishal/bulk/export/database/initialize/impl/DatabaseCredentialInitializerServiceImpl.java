package com.bishal.bulk.export.database.initialize.impl;

import com.bishal.bulk.export.common.mapper.resquest.DataExportRequestMapper;
import com.bishal.bulk.export.common.service.initialize.IDataExportRequestMapperInitializer;
import com.bishal.bulk.export.database.initialize.IDatabaseCredentialInitializerService;
import com.bishal.bulk.export.database.model.DatabaseCredentials;
import com.bishal.bulk.export.database.service.IDatabaseCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DatabaseCredentialInitializerServiceImpl implements IDatabaseCredentialInitializerService {

    @Autowired
    private IDatabaseCredentialService databaseCredentialService;

    @Autowired
    private IDataExportRequestMapperInitializer dataExportRequestMapperInitializer;

    @Override
    public Mono<DatabaseCredentials> getValidDatabaseCredentials() {
        Mono<DatabaseCredentials> databaseCredentials = getDatabaseCredentials(
                                                                dataExportRequestMapperInitializer.getValidRequestDataForEntireDataInCollection()
                                                                );
        return databaseCredentials;
    }

    @Override
    public Mono<DatabaseCredentials> getDatabaseCredentials(final DataExportRequestMapper dataExportRequestMapper) {
        Mono<DatabaseCredentials> databaseCredentials = databaseCredentialService
                .getDatabaseCredentialDetails(dataExportRequestMapper);
        return databaseCredentials;
    }


}
