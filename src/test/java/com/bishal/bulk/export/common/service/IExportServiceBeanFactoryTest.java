package com.bishal.bulk.export.common.service;


import com.bishal.bulk.export.database.service.IDatabaseBeanFactoryTest;
import com.bishal.bulk.export.service.initialize.IInstantExportBeanFactoryTest;

public interface IExportServiceBeanFactoryTest {
    ICommonBeanFactoryTest getCommonBeanFactoryTest();
    IDatabaseBeanFactoryTest getDatabaseBeanFactoryTest();
    IInstantExportBeanFactoryTest getInstantExportBeanFactoryTest();

}
