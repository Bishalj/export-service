package com.bishal.bulk.export.database.service.impl;

import com.bishal.bulk.export.common.exception.NoDataFoundException;
import com.bishal.bulk.export.common.service.initialize.IDataExportRequestMapperInitializer;
import com.bishal.bulk.export.database.model.DatabaseCredentials;
import com.bishal.bulk.export.database.service.IDatabaseCredentialService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient
//@TestPropertySource("classpath:application-test.properties")
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@EnableAutoConfiguration
@ActiveProfiles("test")
public class TestDatabaseCredentialDetailsServiceImplTests {

    @Autowired
    private IDatabaseCredentialService databaseCredentialService;

    @Autowired
    private IDataExportRequestMapperInitializer dataExportRequestMapperInitializer;

    @Value("${env.database.host.url}")
    private static String hostUrlEnvironemntVariableKey;

//    @Test
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
        System.out.println("************************************** " + hostUrlEnvironemntVariableKey + " ***************************************");
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
