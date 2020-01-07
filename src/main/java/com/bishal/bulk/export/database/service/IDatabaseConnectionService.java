package com.bishal.bulk.export.database.service;

import com.bishal.bulk.export.database.model.DatabaseCredentials;
import reactor.core.publisher.Mono;

public interface IDatabaseConnectionService {
    Mono<Void> saveDatabaseConnectionClientInStore(DatabaseCredentials databaseCredentials);
}
