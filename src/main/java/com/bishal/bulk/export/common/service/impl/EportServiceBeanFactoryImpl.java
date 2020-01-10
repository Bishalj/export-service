package com.bishal.bulk.export.common.service.impl;

import com.bishal.bulk.export.common.service.IExportServiceBeanFactory;
import com.bishal.bulk.export.database.service.IDatabaseBeanFactory;
import com.bishal.bulk.export.instant.service.IInstantExportBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EportServiceBeanFactoryImpl implements IExportServiceBeanFactory {

    @Autowired
    private IDatabaseBeanFactory databaseBeanFactory;

    @Autowired
    private IInstantExportBeanFactory instantExportBeanFactory;

    @Override
    public IDatabaseBeanFactory getDatabaseBeanFactory() {
        return databaseBeanFactory;
    }

    @Override
    public IInstantExportBeanFactory getInstantExportBeanFactory() {
        return instantExportBeanFactory;
    }
}
