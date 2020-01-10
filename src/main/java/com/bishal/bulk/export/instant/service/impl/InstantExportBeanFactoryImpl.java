package com.bishal.bulk.export.instant.service.impl;

import com.bishal.bulk.export.instant.service.IFileExportMetaDataService;
import com.bishal.bulk.export.instant.service.IInstantExportBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstantExportBeanFactoryImpl implements IInstantExportBeanFactory {

    @Autowired
    private IFileExportMetaDataService fileExportMetaDataService;

    @Override
    public IFileExportMetaDataService getFileExportMetaDataService() {
        return fileExportMetaDataService;
    }
}
