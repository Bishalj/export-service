package com.bishal.bulk.export.database.service;

import com.bishal.bulk.export.common.mapper.resquest.DataExportRequestMapper;
import com.bishal.bulk.export.database.model.DatabaseCredentials;
import reactor.core.publisher.Mono;

public interface IDatabaseCredentialService {
    Mono<DatabaseCredentials> getDatabaseCredentialDetails(DataExportRequestMapper dataExportRequestMapper);
}
