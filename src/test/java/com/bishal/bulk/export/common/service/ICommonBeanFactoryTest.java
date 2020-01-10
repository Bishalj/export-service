package com.bishal.bulk.export.common.service;

import com.bishal.bulk.export.common.dao.ITestDataDao;
import com.bishal.bulk.export.instant.service.IInstantExportBeanFactory;

public interface ICommonBeanFactoryTest {

    ITestDataDao getTestDataDao();

}
