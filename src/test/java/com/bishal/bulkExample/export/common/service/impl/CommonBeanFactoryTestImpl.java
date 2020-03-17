package com.bishal.bulkExample.export.common.service.impl;

import com.bishal.bulkExample.export.common.dao.ITestDataDao;
import com.bishal.bulkExample.export.common.service.ICommonBeanFactoryTest;
import com.bishal.bulk.export.instant.service.IInstantExportBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonBeanFactoryTestImpl implements ICommonBeanFactoryTest {


    @Autowired
    private ITestDataDao testDataDao;

    @Autowired
    private IInstantExportBeanFactory instantExportBeanFactory;

    @Override
    public ITestDataDao getTestDataDao() {
        return testDataDao;
    }


}
