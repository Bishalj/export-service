package com.bishal.bulk.export.instant.service.impl;

import com.bishal.bulk.export.common.mapper.response.FileMetaDetailsResponse;
import com.bishal.bulk.export.common.mapper.resquest.DataExportRequestMapper;
import com.bishal.bulk.export.database.service.IDatabaseCredentialService;
import com.bishal.bulk.export.database.utils.DatabaseCredentialUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class FileExportMetaDataServiceImplTest {

    @Mock
    private DatabaseCredentialUtils mockDatabaseCredentialUtils;
    @Mock
    private IDatabaseCredentialService mockDatabaseCredentialService;

    @InjectMocks
    private FileExportMetaDataServiceImpl fileExportMetaDataServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void testGetDetailOfFileContainingData() {
        // Setup
        final DataExportRequestMapper dataExportRequestMapper = null;
        final Flux<FileMetaDetailsResponse> expectedResult = null;
        when(mockDatabaseCredentialService.getDatabaseCredentialDetails(null)).thenReturn(null);

        // Run the test
        final Flux<FileMetaDetailsResponse> result = fileExportMetaDataServiceImplUnderTest.getDetailOfFileContainingData(dataExportRequestMapper);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
