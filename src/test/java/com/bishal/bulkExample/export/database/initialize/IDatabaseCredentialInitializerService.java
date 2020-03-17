package com.bishal.bulkExample.export.database.initialize;

import com.bishal.bulk.export.common.mapper.resquest.DataExportRequestMapper;
import com.bishal.bulk.export.database.model.DatabaseCredential;
import reactor.core.publisher.Mono;

public interface IDatabaseCredentialInitializerService {

    Mono<DatabaseCredential> getValidDatabaseCredentials();

    Mono<DatabaseCredential> getDatabaseCredentials(final DataExportRequestMapper dataExportRequestMapper);
}
