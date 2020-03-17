package com.bishal.bulkExample.export.database.initialize.impl;

import com.bishal.bulk.export.common.mapper.resquest.DataExportRequestMapper;
import com.bishal.bulk.export.common.service.IExportServiceBeanFactory;
import com.bishal.bulkExample.export.common.service.IExportServiceBeanFactoryTest;
import com.bishal.bulkExample.export.database.initialize.IDatabaseCredentialInitializerService;
import com.bishal.bulk.export.database.model.DatabaseCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DatabaseCredentialInitializerServiceImpl implements IDatabaseCredentialInitializerService {

    @Autowired
    private IExportServiceBeanFactory exportServiceBeanFactory;

    @Autowired
    private IExportServiceBeanFactoryTest exportServiceBeanFactoryTest;

    @Override
    public Mono<DatabaseCredential> getValidDatabaseCredentials() {
        Mono<DatabaseCredential> databaseCredentials = getDatabaseCredentials(
                                                                    exportServiceBeanFactoryTest
                                                                        .getInstantExportBeanFactoryTest()
                                                                        .getDataExportRequestMapperInitializer()
                                                                        .getValidRequestDataForEntireDataInCollection()
                                                                );
        return databaseCredentials;
    }

    @Override
    public Mono<DatabaseCredential> getDatabaseCredentials(final DataExportRequestMapper dataExportRequestMapper) {
        Mono<DatabaseCredential> databaseCredentials = exportServiceBeanFactory
                .getDatabaseBeanFactory()
                .getDatabaseCredentialService()
                .getDatabaseCredentialDetails(dataExportRequestMapper);
        return databaseCredentials;
    }


}
