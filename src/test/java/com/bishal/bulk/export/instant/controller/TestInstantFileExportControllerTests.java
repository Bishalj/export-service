package com.bishal.bulk.export.instant.controller;


import com.bishal.bulk.export.common.dao.ITestDataDao;
import com.bishal.bulk.export.common.mapper.response.FileMetaDetailsResponse;
import com.bishal.bulk.export.common.mapper.resquest.DataExportRequestMapper;
import com.bishal.bulk.export.common.service.initialize.IDataExportRequestMapperInitializer;
import com.bishal.bulk.export.instant.service.IFileExportMetaDataService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.bishal.bulk.export.common.constants.ApiRouteUrl.InstantController.EXPORT_DATA;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TestInstantFileExportControllerTests {


    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private IFileExportMetaDataService fileExportMetaDataService;

    @Autowired
    private ITestDataDao testDataDao;

    @Autowired
    private IDataExportRequestMapperInitializer dataExportRequestMapperInitializer;

//    @Test
    public void getFileExportMetaDataFromDatabase(){
        try {
            testDataDao.insertDummyData();

        } catch (Exception e) {
            Assert.fail();
        }
        final DataExportRequestMapper dataExportRequestMapper = dataExportRequestMapperInitializer.getValidRequestDataForEntireDataInCollection();
        Flux<FileMetaDetailsResponse> fileMetaDetailsResponseFlux =
                webTestClient.post()
                    .uri(EXPORT_DATA)
                    .body(Mono.just(dataExportRequestMapper), DataExportRequestMapper.class)
                    .accept(MediaType.valueOf(APPLICATION_JSON_VALUE))
                    .exchange()
                    .expectStatus().isOk()
                    .returnResult(FileMetaDetailsResponse.class)
                    .getResponseBody();

        StepVerifier.create(fileMetaDetailsResponseFlux)
                .expectSubscription()
                .consumeRecordedWith( fileDetailsResponseMappers -> fileDetailsResponseMappers
                                                                       .parallelStream()
                                                                       .map( fileMetaDetailsResponse -> isFileDetailsResponseValid(fileMetaDetailsResponse))

                )
                .verifyComplete();

    }

    private boolean isFileDetailsResponseValid(FileMetaDetailsResponse fileMetaDetailsResponse) {
        Assert.assertNotNull(fileMetaDetailsResponse);
        Assert.assertNotNull(fileMetaDetailsResponse.getFileName());
        Assert.assertNotNull(fileMetaDetailsResponse.getCreatedAt());
        Assert.assertNotNull(fileMetaDetailsResponse.getS3FileUrl());
        return true;
    }

    @Test
    public void testGetFileExportNoContentTests(){
        final DataExportRequestMapper dataExportRequestMapper = dataExportRequestMapperInitializer.getValidRequestDataForEntireDataInCollection();
                webTestClient.post()
                        .uri(EXPORT_DATA)
                        .body(Mono.just(dataExportRequestMapper), DataExportRequestMapper.class)
                        .accept(MediaType.valueOf(APPLICATION_JSON_VALUE))
                        .exchange()
                        .expectStatus().isNotFound();
    }

    @Test
    public void getFileDetails_InvalidRequestDataWithoutQueryFiled() {

                webTestClient.post()
                        .uri(EXPORT_DATA)
                        .body(
                                Mono.just(dataExportRequestMapperInitializer.getInvalidRequestData_NoQueryPresent()),
                                DataExportRequestMapper.class
                        )
                        .accept(MediaType.valueOf(APPLICATION_JSON_VALUE))
                        .exchange()
                        .expectStatus().isBadRequest();
    }

    @Test
    public void getFileDetails_InvalidRequestDataWithoutBatchSizePerFileFiled() {

        webTestClient.post()
                .uri(EXPORT_DATA)
                .body(
                        Mono.just(dataExportRequestMapperInitializer.getInvalidRequestData_NoBatchSizePerFilePresent()),
                        DataExportRequestMapper.class
                )
                .accept(MediaType.valueOf(APPLICATION_JSON_VALUE))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void getFileDetails_InvalidRequestDataWithoutNoDatabaseUniqueKeyFiled() {

        webTestClient.post()
                .uri(EXPORT_DATA)
                .body(
                        Mono.just(dataExportRequestMapperInitializer.getInvalidRequestData_NoDatabaseUniqueKeyPresent()),
                        DataExportRequestMapper.class
                )
                .accept(MediaType.valueOf(APPLICATION_JSON_VALUE))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void getFileDetails_InvalidRequestDataWithoutNoOrderedField() {

        webTestClient.post()
                .uri(EXPORT_DATA)
                .body(
                        Mono.just(dataExportRequestMapperInitializer.getInvalidRequestData_NoOrderedFieldPresent()),
                        DataExportRequestMapper.class
                )
                .accept(MediaType.valueOf(APPLICATION_JSON_VALUE))
                .exchange()
                .expectStatus().isBadRequest();
    }

}
