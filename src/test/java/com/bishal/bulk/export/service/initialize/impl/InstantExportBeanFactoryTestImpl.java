package com.bishal.bulk.export.service.initialize.impl;

import com.bishal.bulk.export.service.initialize.IDataExportRequestMapperInitializer;
import com.bishal.bulk.export.service.initialize.IInstantExportBeanFactoryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstantExportBeanFactoryTestImpl implements IInstantExportBeanFactoryTest {

    @Autowired
    private IDataExportRequestMapperInitializer dataExportRequestMapperInitializer;

    @Override
    public IDataExportRequestMapperInitializer getDataExportRequestMapperInitializer() {
        return dataExportRequestMapperInitializer;
    }
}
