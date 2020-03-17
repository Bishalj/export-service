package com.bishal.bulkExample.export.database.service.impl;

import com.bishal.bulkExample.export.database.initialize.IDatabaseCredentialInitializerService;
import com.bishal.bulkExample.export.database.service.IDatabaseBeanFactoryTest;
import com.bishal.bulkExample.export.service.initialize.IDataExportRequestMapperInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseBeanFactoryTestImpl implements IDatabaseBeanFactoryTest {


    @Autowired
    private IDataExportRequestMapperInitializer dataExportRequestMapperInitializer;

    @Autowired
    private IDatabaseCredentialInitializerService databaseCredentialInitializerService;

    @Override
    public IDataExportRequestMapperInitializer getDataExportRequestMapperInitializer() {
        return dataExportRequestMapperInitializer;
    }

    @Override
    public IDatabaseCredentialInitializerService getDatabaseCredentialInitializerService() {
        return databaseCredentialInitializerService;
    }
}
