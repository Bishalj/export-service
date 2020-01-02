package com.bishal.bulk.export.database.service.impl;

import com.bishal.bulk.export.common.exception.NoDataFoundException;
import com.bishal.bulk.export.common.service.initialize.IDataExportRequestMapperInitializer;
import com.bishal.bulk.export.database.model.DatabaseCredentials;
import com.bishal.bulk.export.database.service.IDatabaseCredentialService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DatabaseCredentialDetailsServiceImplTests {

    @Autowired
    private IDatabaseCredentialService databaseCredentialService;

    @Autowired
    private IDataExportRequestMapperInitializer dataExportRequestMapperInitializer;

    @Test
    public void fetchDatabaseCredentials_EnvironmentVariablesPresent_SuccessfullyFetchedDatabaseDetails(){
        Mono<DatabaseCredentials> databaseCredentials = databaseCredentialService
                                                        .getDatabaseCredentialDetails(
                                                                dataExportRequestMapperInitializer.getRequestForEntireDataInCollection()
                                                        );

        StepVerifier.create(databaseCredentials)
                .expectSubscription()
                .consumeNextWith( databaseCredentialDetails -> isDatabaseCredentialDetailsValid(databaseCredentialDetails))
                .verifyComplete();


    }

    @Test
    public void fetchDatabaseCredentialsTest_EnvironmentVariablesNotPresent_failedFetchingDatabaseDetails(){
        Mono<DatabaseCredentials> databaseCredentials = databaseCredentialService
                .getDatabaseCredentialDetails(
                        dataExportRequestMapperInitializer.getRequestForEntireDataInCollection()
                );

        StepVerifier.create(databaseCredentials)
                .expectError(NoDataFoundException.class)
                .verify();


    }

    private void isDatabaseCredentialDetailsValid(final DatabaseCredentials databaseCredentials) {
        Assert.assertNotNull(databaseCredentials);
        Assert.assertNotNull(databaseCredentials.getHostUrl());
        Assert.assertNotNull(databaseCredentials.getPortNumber());
        Assert.assertNotNull(databaseCredentials.getUsername());
        Assert.assertNotNull(databaseCredentials.getPassword());
    }



}
