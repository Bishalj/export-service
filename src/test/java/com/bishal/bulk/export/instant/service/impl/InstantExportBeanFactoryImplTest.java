package com.bishal.bulk.export.instant.service.impl;

import com.bishal.bulk.export.database.service.IDatabaseConnectionService;
import com.bishal.bulk.export.instant.service.IFileExportMetaDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

class InstantExportBeanFactoryImplTest {

    @Mock
    private IFileExportMetaDataService mockFileExportMetaDataService;

    @InjectMocks
    private InstantExportBeanFactoryImpl instantExportBeanFactoryImplUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    public void getFileExportMetaDataService_validFileExportMetaDataService_returnFileExportMetaDataService() {
        assertTrue(instantExportBeanFactoryImplUnderTest
                .getFileExportMetaDataService() instanceof IFileExportMetaDataService);
    }
}
