package com.bishal.bulk.export.instant.controller;

import com.bishal.bulk.export.common.mapper.response.FileMetaDetailsResponse;
import com.bishal.bulk.export.common.mapper.resquest.DataExportRequestMapper;
import com.bishal.bulk.export.common.service.IExportServiceBeanFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class InstantFileExportControllerTest {

    @Mock
    private IExportServiceBeanFactory mockExportServiceBeanFactory;

    @InjectMocks
    private InstantFileExportController instantFileExportControllerUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void testGetFileExportDetail() {
        // Setup
        final DataExportRequestMapper dataExportRequestMapper = null;
        final ResponseEntity<Flux<FileMetaDetailsResponse>> expectedResult = null;
        when(mockExportServiceBeanFactory.getInstantExportBeanFactory()).thenReturn(null);

        // Run the test
        final ResponseEntity<Flux<FileMetaDetailsResponse>> result = instantFileExportControllerUnderTest.getFileExportDetail(dataExportRequestMapper);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
