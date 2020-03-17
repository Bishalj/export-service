package com.bishal.bulkExample.export.common.service;


import com.bishal.bulkExample.export.database.service.IDatabaseBeanFactoryTest;
import com.bishal.bulkExample.export.service.initialize.IInstantExportBeanFactoryTest;

public interface IExportServiceBeanFactoryTest {
    ICommonBeanFactoryTest getCommonBeanFactoryTest();
    IDatabaseBeanFactoryTest getDatabaseBeanFactoryTest();
    IInstantExportBeanFactoryTest getInstantExportBeanFactoryTest();

}
