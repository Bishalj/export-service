package com.bishal.bulk.export.database.service;

import com.bishal.bulk.export.database.model.DatabaseCredential;
import reactor.core.publisher.Mono;

public interface IDatabaseConnectionService {
    Mono<Void> processDatabaseCredentialForDatabaseConnectionStore(DatabaseCredential databaseCredential);
}
