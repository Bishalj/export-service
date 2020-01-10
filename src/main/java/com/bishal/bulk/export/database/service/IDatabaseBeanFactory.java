package com.bishal.bulk.export.database.service;

import com.bishal.bulk.export.database.utils.DatabaseCredentialUtils;

public interface IDatabaseBeanFactory {

    IDatabaseConnectionService getDatabaseConnectionService();

    IDatabaseCredentialService getDatabaseCredentialService();

    DatabaseCredentialUtils  getDatabaseCredentialUtils();
}
