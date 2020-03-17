package com.bishal.bulk.export.database.service.impl;

import com.bishal.bulk.export.database.service.IDatabaseBeanFactory;
import com.bishal.bulk.export.database.service.IDatabaseConnectionService;
import com.bishal.bulk.export.database.service.IDatabaseCredentialService;
import com.bishal.bulk.export.database.utils.DatabaseCredentialUtils;
import com.bishal.bulk.export.instant.service.IInstantExportBeanFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

class DatabaseBeanFactoryImplTest {

    @Mock
    private IDatabaseConnectionService mockDatabaseConnectionService;
    @Mock
    private IDatabaseCredentialService mockDatabaseCredentialService;
    @Mock
    private DatabaseCredentialUtils mockDatabaseCredentialUtils;

    @InjectMocks
    private DatabaseBeanFactoryImpl databaseBeanFactoryImplUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    public void getDatabaseConnectionService_validDatabaseConnectionService_returnDatabaseConnectionService() {
        assertTrue(databaseBeanFactoryImplUnderTest
                .getDatabaseConnectionService() instanceof IDatabaseConnectionService);
    }

    @Test
    public void getDatabaseCredentialService_validDatabaseCredentialService_returnDatabaseCredentialServiceService() {
        assertTrue(databaseBeanFactoryImplUnderTest
                .getDatabaseCredentialService() instanceof IDatabaseCredentialService);
    }

    @Test
    public void getDatabaseCredentialUtils_validDatabaseCredentialUtils_returnDatabaseCredentialUtilsService() {
        assertTrue(databaseBeanFactoryImplUnderTest
                .getDatabaseCredentialUtils() instanceof DatabaseCredentialUtils);
    }
}
