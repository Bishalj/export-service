package com.bishal.bulk.export.database.service.impl;


import com.bishal.bulk.export.database.initialize.IDatabaseCredentialInitializerService;
import com.bishal.bulk.export.database.service.IDatabaseConnectionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@EnableAutoConfiguration
@ActiveProfiles("test")
public class TestDatabaseConnectionServiceImpl {

    @Autowired
    private IDatabaseConnectionService databaseConnectionService;

    @Autowired
    private IDatabaseCredentialInitializerService databaseCredentialInitializerService;

    @Test
    public void setDatabaseConnectionClientInStore_ValidDatabaseConnectionClient_SuccessfullyStoredDatabaseConnectionClient(){

        Mono<Void> databaseConnectionClientStoreResponse = databaseCredentialInitializerService
                                                                    .getValidDatabaseCredentials()
                                                                    .flatMap(databaseCredentials -> databaseConnectionService.saveDatabaseConnectionClientInStore(databaseCredentials));

        StepVerifier.create(databaseConnectionClientStoreResponse)
                    .expectSubscription()
                    .verifyComplete();
    }
}
