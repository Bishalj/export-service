package com.bishal.bulk.export.database.service.impl;

import com.bishal.bulk.export.common.exception.NoDataFoundException;
import com.bishal.bulk.export.common.mapper.resquest.DataExportRequestMapper;
import com.bishal.bulk.export.common.service.IExportServiceBeanFactory;
import com.bishal.bulk.export.database.model.DatabaseCredentials;
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
    public Mono<DatabaseCredentials> getDatabaseCredentialDetails(DataExportRequestMapper dataExportRequestMapper){

        final Mono<DatabaseCredentials> databaseCredentialMono = exportServiceBeanFactory
                                                                    .getDatabaseBeanFactory()
                                                                    .getDatabaseCredentialUtils()
                                                                    .getDatabaseCredential(dataExportRequestMapper.getDatabaseUniqueKey(), dataExportRequestMapper.getDatabaseName());
        return databaseCredentialMono
                .flatMap(databaseCredential -> processDatabaseCredentials(databaseCredential));

    }

    private Mono<DatabaseCredentials> processDatabaseCredentials(DatabaseCredentials databaseCredential) {
        if(isDatabaseCredentialsInvalid(databaseCredential))
            return Mono.error(new NoDataFoundException("Invalid database credentials"));
        return Mono.just(databaseCredential);
    }

    private boolean isDatabaseCredentialsInvalid(DatabaseCredentials databaseCredentials) {
        return  ObjectUtils.isEmpty(databaseCredentials) ||
                StringUtils.isEmpty(databaseCredentials.getHostUrl()) ||
                ObjectUtils.isEmpty(databaseCredentials.getPortNumber()) ||
                StringUtils.isEmpty(databaseCredentials.getUsername()) ||
                StringUtils.isEmpty(databaseCredentials.getPassword());
    }
}
