package com.bishal.bulk.export.common.service;

import com.bishal.bulk.export.database.service.IDatabaseBeanFactory;
import com.bishal.bulk.export.instant.service.IInstantExportBeanFactory;

public interface IExportServiceBeanFactory {

     IDatabaseBeanFactory getDatabaseBeanFactory();

     IInstantExportBeanFactory getInstantExportBeanFactory();

}
