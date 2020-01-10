package com.bishal.bulk.export.database.service;

import com.bishal.bulk.export.database.initialize.IDatabaseCredentialInitializerService;
import com.bishal.bulk.export.service.initialize.IDataExportRequestMapperInitializer;

public interface IDatabaseBeanFactoryTest {


    IDataExportRequestMapperInitializer getDataExportRequestMapperInitializer();

    IDatabaseCredentialInitializerService getDatabaseCredentialInitializerService();
}
