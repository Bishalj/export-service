package com.bishal.bulk.export.database.initialize.impl;

import com.bishal.bulk.export.common.service.initialize.IDataExportRequestMapperInitializer;
import com.bishal.bulk.export.database.initialize.IDatabaseCredentialInitializerService;
import com.bishal.bulk.export.database.model.DatabaseCredentials;
import com.bishal.bulk.export.database.service.IDatabaseCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

public class DatabaseCredentialInitializerServiceImpl implements IDatabaseCredentialInitializerService {

    @Autowired
    private IDatabaseCredentialService databaseCredentialService;

    @Autowired
    private IDataExportRequestMapperInitializer dataExportRequestMapperInitializer;

    @Override
    public Mono<DatabaseCredentials> getValidDatabaseCredentials() {
        Mono<DatabaseCredentials> databaseCredentials = databaseCredentialService
                .getDatabaseCredentialDetails(
                        dataExportRequestMapperInitializer.getRequestForEntireDataInCollection()
                );
        return databaseCredentials;
    }
}
