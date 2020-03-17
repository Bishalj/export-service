package com.bishal.bulkExample.export.database.service;

import com.bishal.bulkExample.export.database.initialize.IDatabaseCredentialInitializerService;
import com.bishal.bulkExample.export.service.initialize.IDataExportRequestMapperInitializer;

public interface IDatabaseBeanFactoryTest {


    IDataExportRequestMapperInitializer getDataExportRequestMapperInitializer();

    IDatabaseCredentialInitializerService getDatabaseCredentialInitializerService();
}
