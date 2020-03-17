package com.bishal.bulk.export.database.service.impl;

import com.bishal.bulk.export.common.exception.NoDataFoundException;
import com.bishal.bulk.export.common.mapper.resquest.DataExportRequestMapper;
import com.bishal.bulk.export.common.service.IExportServiceBeanFactory;
import com.bishal.bulk.export.database.model.DatabaseCredential;
import com.bishal.bulk.export.database.service.IDatabaseCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

@Service
public class DatabaseCredentialServiceImpl implements IDatabaseCredentialService {


    @Autowired
    private IExportServiceBeanFactory exportServiceBeanFactory;

    @Override
    public Mono<DatabaseCredential> getDatabaseCredentialDetails(DataExportRequestMapper dataExportRequestMapper){

        final Mono<DatabaseCredential> databaseCredentialMono = exportServiceBeanFactory
                                                                    .getDatabaseBeanFactory()
                                                                    .getDatabaseCredentialUtils()
                                                                    .getDatabaseCredential(dataExportRequestMapper.getDatabaseUniqueKey(), dataExportRequestMapper.getDatabaseName());
        return databaseCredentialMono
                .flatMap(databaseCredential -> processDatabaseCredentials(databaseCredential));

    }

    private Mono<DatabaseCredential> processDatabaseCredentials(DatabaseCredential databaseCredential) {
        if(isDatabaseCredentialsInvalid(databaseCredential))
            return Mono.error(new NoDataFoundException("Invalid database credentials"));
        return Mono.just(databaseCredential);
    }

    private boolean isDatabaseCredentialsInvalid(DatabaseCredential databaseCredential) {
        return  ObjectUtils.isEmpty(databaseCredential) ||
                StringUtils.isEmpty(databaseCredential.getHostUrl()) ||
                ObjectUtils.isEmpty(databaseCredential.getPortNumber()) ||
                StringUtils.isEmpty(databaseCredential.getUsername()) ||
                StringUtils.isEmpty(databaseCredential.getPassword());
    }
}
