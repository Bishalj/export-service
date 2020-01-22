package com.bishal.bulk.export.instant.service.impl;

import com.bishal.bulk.export.common.exception.NoDataFoundException;
import com.bishal.bulk.export.common.mapper.response.FileMetaDetailsResponse;
import com.bishal.bulk.export.common.service.IExportServiceBeanFactory;
import com.bishal.bulk.export.common.service.IExportServiceBeanFactoryTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestFileExportMetaDataServiceImplTest {

    @Autowired
    private IExportServiceBeanFactory exportServiceBeanFactory;

    @Autowired
    private IExportServiceBeanFactoryTest exportServiceBeanFactoryTest;

//    @Test
    public void testGetDetailOfFileContainingData() {
        try {
            exportServiceBeanFactoryTest
                    .getCommonBeanFactoryTest()
                    .getTestDataDao()
                    .insertDummyData();
        } catch (Exception e) {
            Assert.fail();
        }
        final Flux<FileMetaDetailsResponse> fileMetaDetailsResponseFlux = exportServiceBeanFactory
                                                                        .getInstantExportBeanFactory()
                                                                        .getFileExportMetaDataService()
                                                                        .getDetailOfFileContainingData(
                                                                                exportServiceBeanFactoryTest
                                                                                    .getInstantExportBeanFactoryTest()
                                                                                    .getDataExportRequestMapperInitializer()
                                                                                    .getValidRequestDataForEntireDataInCollection()
                                                                        );
        StepVerifier.create(fileMetaDetailsResponseFlux)
                .expectSubscription()
                .consumeRecordedWith( fileDetailsResponseMappers -> fileDetailsResponseMappers
                        .parallelStream()
                        .map( fileMetaDetailsResponse -> isFileDetailsResponseValid(fileMetaDetailsResponse))

                )
                .verifyComplete();
    }
//    @Test
    public void testGetDetailOfFileContainingData_NoDataFound() {
        final Flux<FileMetaDetailsResponse> fileMetaDetailsResponseFlux = exportServiceBeanFactory
                .getInstantExportBeanFactory()
                .getFileExportMetaDataService()
                .getDetailOfFileContainingData(
                        exportServiceBeanFactoryTest
                                .getInstantExportBeanFactoryTest()
                                .getDataExportRequestMapperInitializer()
                                .getValidRequestDataForEntireDataInCollection()
                ).log();
        StepVerifier.create(fileMetaDetailsResponseFlux)
                .expectSubscription()
                .consumeRecordedWith( fileDetailsResponseMappers -> fileDetailsResponseMappers
                        .parallelStream()
                        .map( fileMetaDetailsResponse -> isFileDetailsResponseValid(fileMetaDetailsResponse))

                )
                .verifyComplete();
    }

    @Test
    public void getFileDetails_InvalidRequestDataWithoutQuery() {
        final Flux<FileMetaDetailsResponse> fileMetaDetailsResponseFlux = exportServiceBeanFactory
                .getInstantExportBeanFactory()
                .getFileExportMetaDataService()
                .getDetailOfFileContainingData(
                        exportServiceBeanFactoryTest
                                .getInstantExportBeanFactoryTest()
                                .getDataExportRequestMapperInitializer()
                                .getInvalidRequestData_NoQueryPresent()
                ).log();

        StepVerifier.create(fileMetaDetailsResponseFlux)
                .expectSubscription()
                .expectError(IllegalArgumentException.class)
                .verify();
    }

    @Test
    public void getFileDetails_InvalidRequestDataWithoutBatchSizePerFileFiled() {

        final Flux<FileMetaDetailsResponse> fileMetaDetailsResponseFlux = exportServiceBeanFactory
                .getInstantExportBeanFactory()
                .getFileExportMetaDataService()
                .getDetailOfFileContainingData(
                        exportServiceBeanFactoryTest
                                .getInstantExportBeanFactoryTest()
                                .getDataExportRequestMapperInitializer()
                                .getInvalidRequestData_NoBatchSizePerFilePresent()
                ).log();

        StepVerifier.create(fileMetaDetailsResponseFlux)
                .expectSubscription()
                .expectError(IllegalArgumentException.class)
                .verify();
    }

    @Test
    public void getFileDetails_InvalidRequestDataWithoutNoDatabaseUniqueKeyFiled() {

        final Flux<FileMetaDetailsResponse> fileMetaDetailsResponseFlux = exportServiceBeanFactory
                .getInstantExportBeanFactory()
                .getFileExportMetaDataService()
                .getDetailOfFileContainingData(
                        exportServiceBeanFactoryTest
                                .getInstantExportBeanFactoryTest()
                                .getDataExportRequestMapperInitializer()
                                .getInvalidRequestData_NoDatabaseUniqueKeyPresent()
                ).log();

        StepVerifier.create(fileMetaDetailsResponseFlux)
                .expectSubscription()
                .expectError(IllegalArgumentException.class)
                .verify();
    }

    @Test
    public void getFileDetails_InvalidRequestDataWithoutNoOrderedField() {

        final Flux<FileMetaDetailsResponse> fileMetaDetailsResponseFlux = exportServiceBeanFactory
                .getInstantExportBeanFactory()
                .getFileExportMetaDataService()
                .getDetailOfFileContainingData(
                        exportServiceBeanFactoryTest
                                .getInstantExportBeanFactoryTest()
                                .getDataExportRequestMapperInitializer()
                                .getInvalidRequestData_NoOrderedFieldPresent()
                ).log();

        StepVerifier.create(fileMetaDetailsResponseFlux)
                .expectSubscription()
                .expectError(IllegalArgumentException.class)
                .verify();
    }

    @Test
    public void getFileDetails_InvalidRequestDataWithoutDatabaseName() {

        final Flux<FileMetaDetailsResponse> fileMetaDetailsResponseFlux = exportServiceBeanFactory
                .getInstantExportBeanFactory()
                .getFileExportMetaDataService()
                .getDetailOfFileContainingData(
                        exportServiceBeanFactoryTest
                                .getInstantExportBeanFactoryTest()
                                .getDataExportRequestMapperInitializer()
                                .getInvalidRequestData_NoDatabaseNamePresent()
                ).log();

        StepVerifier.create(fileMetaDetailsResponseFlux)
                .expectSubscription()
                .expectError(IllegalArgumentException.class)
                .verify();
    }

    @Test
    public void getFileDetails_InvalidRequestData_WithNullData() {

        final Flux<FileMetaDetailsResponse> fileMetaDetailsResponseFlux = exportServiceBeanFactory
                .getInstantExportBeanFactory()
                .getFileExportMetaDataService()
                .getDetailOfFileContainingData(
                        exportServiceBeanFactoryTest
                                .getInstantExportBeanFactoryTest()
                                .getDataExportRequestMapperInitializer()
                                .getInvalidRequestData_NoRequestDataPresent()
                ).log();

        StepVerifier.create(fileMetaDetailsResponseFlux)
                .expectSubscription()
                .expectError(IllegalArgumentException.class)
                .verify();
    }

    private boolean isFileDetailsResponseValid(FileMetaDetailsResponse fileMetaDetailsResponse) {
        Assert.assertNotNull(fileMetaDetailsResponse);
        Assert.assertNotNull(fileMetaDetailsResponse.getFileName());
        Assert.assertNotNull(fileMetaDetailsResponse.getCreatedAt());
        Assert.assertNotNull(fileMetaDetailsResponse.getS3FileUrl());
        return true;
    }
}
