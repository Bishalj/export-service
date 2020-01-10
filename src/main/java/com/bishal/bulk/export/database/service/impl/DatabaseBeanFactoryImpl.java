package com.bishal.bulk.export.database.service.impl;

import com.bishal.bulk.export.database.service.IDatabaseBeanFactory;
import com.bishal.bulk.export.database.service.IDatabaseConnectionService;
import com.bishal.bulk.export.database.service.IDatabaseCredentialService;
import com.bishal.bulk.export.database.utils.DatabaseCredentialUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseBeanFactoryImpl implements IDatabaseBeanFactory {

    @Autowired
    private IDatabaseConnectionService databaseConnectionService;

    @Autowired
    private IDatabaseCredentialService databaseCredentialService;

    @Autowired
    private DatabaseCredentialUtils databaseCredentialUtils;

    @Override
    public IDatabaseConnectionService getDatabaseConnectionService() {
        return databaseConnectionService;
    }

    @Override
    public IDatabaseCredentialService getDatabaseCredentialService() {
        return databaseCredentialService;
    }

    @Override
    public DatabaseCredentialUtils getDatabaseCredentialUtils() {
        return databaseCredentialUtils;
    }
}
