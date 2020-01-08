package com.bishal.bulk.export.database.initialize;

import com.bishal.bulk.export.common.mapper.resquest.DataExportRequestMapper;
import com.bishal.bulk.export.database.model.DatabaseCredentials;
import reactor.core.publisher.Mono;

public interface IDatabaseCredentialInitializerService {

    Mono<DatabaseCredentials> getValidDatabaseCredentials();

    Mono<DatabaseCredentials> getDatabaseCredentials(final DataExportRequestMapper dataExportRequestMapper);
}
