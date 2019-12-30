package com.bishal.bulk.export.instant.controller;


import com.bishal.bulk.export.common.dao.ITestDataDao;
import com.bishal.bulk.export.common.mapper.response.FileDetailsResponseMapper;
import com.bishal.bulk.export.common.mapper.resquest.DataExportRequestMapper;
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
public class TestInstantFileExportControllerTest {


    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private IFileExportMetaDataService fileExportMetaDataService;

    @Autowired
    private ITestDataDao testDataDao;


    public DataExportRequestMapper initializeData(){
        final DataExportRequestMapper dataExportRequestMapper = new DataExportRequestMapper();
        dataExportRequestMapper.setBatchSizePerFile(100000l);
        dataExportRequestMapper.setDatabaseQuery("{}");
        dataExportRequestMapper.setDatabaseUniqueKey("LOCAL");
        dataExportRequestMapper.setDataNeedsToBeOrdered(false);
        return dataExportRequestMapper;
    }



//    @Test
    public void getFileExportMetaDataFromDatabase(){
        try {
            testDataDao.insertDummyData();

        } catch (Exception e) {
            Assert.fail();
        }
        final DataExportRequestMapper dataExportRequestMapper = initializeData();
        Flux<FileDetailsResponseMapper> fileExportMetaDataFlux =
                webTestClient.post()
                    .uri(EXPORT_DATA)
                    .body(Mono.just(dataExportRequestMapper), DataExportRequestMapper.class)
                    .accept(MediaType.valueOf(APPLICATION_JSON_VALUE))
                    .exchange()
                    .expectStatus().isOk()
                    .returnResult(FileDetailsResponseMapper.class)
                    .getResponseBody();

        StepVerifier.create(fileExportMetaDataFlux)
                .expectSubscription()
                .expectNextCount(1)
                .verifyComplete();

    }

    @Test
    public void testGetFileExportNoContentTest(){
        final DataExportRequestMapper dataExportRequestMapper = initializeData();
                webTestClient.post()
                        .uri(EXPORT_DATA)
                        .body(Mono.just(dataExportRequestMapper), DataExportRequestMapper.class)
                        .accept(MediaType.valueOf(APPLICATION_JSON_VALUE))
                        .exchange()
                        .expectStatus().isNotFound();
    }


}
