package com.bishal.bulk.export.common.service.impl;

import com.bishal.bulk.export.database.service.IDatabaseBeanFactory;
import com.bishal.bulk.export.instant.service.IInstantExportBeanFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

class ExportServiceBeanFactoryImplTest {

    @Mock
    private IDatabaseBeanFactory mockDatabaseBeanFactory;
    @Mock
    private IInstantExportBeanFactory mockInstantExportBeanFactory;

    @InjectMocks
    private ExportServiceBeanFactoryImpl exportServiceBeanFactoryImplUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    public void getDatabaseBeanFactory_validDatabaseBeanFactory_returnDatabaseBeanFactoryService(){
        assertTrue(exportServiceBeanFactoryImplUnderTest
                .getDatabaseBeanFactory() instanceof IDatabaseBeanFactory);
    }

    @Test
    public void getInstantExportBeanFactory_validIInstantExportBeanFactory_returnIInstantExportBeanFactoryService(){
        assertTrue(exportServiceBeanFactoryImplUnderTest
                .getInstantExportBeanFactory() instanceof IInstantExportBeanFactory);
    }
}


