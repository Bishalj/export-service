package com.bishal.bulk.export.database.service.impl;

import com.bishal.bulk.export.database.model.DatabaseCredentials;
import com.bishal.bulk.export.database.service.IDatabaseConnectionService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DatabaseConnectionServiceImpl implements IDatabaseConnectionService {

    @Override
    public Mono<Void> processDatabaseCredentialForDatabaseConnectionStore(DatabaseCredentials databaseCredentials) {
        return Mono.error(new Exception());
    }
}
