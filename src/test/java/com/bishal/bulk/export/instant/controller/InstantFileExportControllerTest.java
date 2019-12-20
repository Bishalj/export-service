package com.bishal.bulk.export.instant.controller;


import com.bishal.bulk.export.common.mapper.response.FileDetailsResponseMapper;
import com.bishal.bulk.export.common.mapper.resquest.DataExportRequestMapper;
import com.bishal.bulk.export.instant.service.IFileExportMetaDataService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebTestClient
@DirtiesContext
public class InstantFileExportControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private IFileExportMetaDataService fileExportMetaDataService;



    @Before
    public void initializeData(){

    }

    @Test
    public void getFileExportMetaDataFromDatabase(){
        DataExportRequestMapper dataExportRequestMapper = new DataExportRequestMapper();
        Flux<FileDetailsResponseMapper> fileExportMetaDataFlux = fileExportMetaDataService
                                                                                    .getFilesMetaDetailsContainingData(dataExportRequestMapper);


    }


}
