package com.bishal.bulk.export.common.service.impl;

import com.bishal.bulk.export.common.service.ICommonBeanFactoryTest;
import com.bishal.bulk.export.common.service.IExportServiceBeanFactoryTest;
import com.bishal.bulk.export.database.service.IDatabaseBeanFactoryTest;
import com.bishal.bulk.export.service.initialize.IInstantExportBeanFactoryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExportServiceBeanFactoryTestImpl implements IExportServiceBeanFactoryTest {

    @Autowired
    private ICommonBeanFactoryTest commonBeanFactoryTest;

    @Autowired
    private IDatabaseBeanFactoryTest databaseBeanFactoryTest;

    @Autowired
    private IInstantExportBeanFactoryTest instantExportBeanFactoryTest;


    @Override
    public ICommonBeanFactoryTest getCommonBeanFactoryTest() {
        return commonBeanFactoryTest;
    }

    @Override
    public IDatabaseBeanFactoryTest getDatabaseBeanFactoryTest() {
        return databaseBeanFactoryTest;
    }

    @Override
    public IInstantExportBeanFactoryTest getInstantExportBeanFactoryTest() {
        return instantExportBeanFactoryTest;
    }

}
