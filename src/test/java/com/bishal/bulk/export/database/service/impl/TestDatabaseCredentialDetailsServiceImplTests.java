package com.bishal.bulk.export.database.service.impl;

import com.bishal.bulk.export.common.exception.NoDataFoundException;
import com.bishal.bulk.export.common.mapper.resquest.DataExportRequestMapper;
import com.bishal.bulk.export.common.service.IExportServiceBeanFactory;
import com.bishal.bulk.export.common.service.IExportServiceBeanFactoryTest;
import com.bishal.bulk.export.database.model.DatabaseCredential;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;
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
public class TestDatabaseCredentialDetailsServiceImplTests {

    @Autowired
    private IExportServiceBeanFactoryTest exportServiceBeanFactoryTest;

    @Autowired
    private IExportServiceBeanFactory exportServiceBeanFactory;


    @ClassRule
    public final static EnvironmentVariables environmentVariables
            = new EnvironmentVariables();


    @Test
    public void fetchDatabaseCredentials_NoEnvironmentVariablesPresent_FailedToFetchedDatabaseDetails(){
        Mono<DatabaseCredential> databaseCredentials = exportServiceBeanFactoryTest
                                                            .getDatabaseBeanFactoryTest()
                                                            .getDatabaseCredentialInitializerService()
                                                            .getValidDatabaseCredentials();

        StepVerifier.create(databaseCredentials)
                .expectSubscription()
                .expectError(NoDataFoundException.class)
                .verify();
    }


//    @Test
//    public void setDatabaseConnectionClientInStore_ValidDatabaseConnectionClient_SuccessfullyStoredDatabaseConnectionClient(){
//        environmentVariables.set("DATABASE_PORT_LOCAL", "27017");
//        environmentVariables.set("DATABASE_USERNAME_LOCAL", "bishal");
//        environmentVariables.set("DATABASE_PASSWORD_LOCAL", "bishal");
//        environmentVariables.set("DATABASE_HOST_URL_LOCAL", "localhost");
//        final DataExportRequestMapper dataExportRequestMapper = exportServiceBeanFactoryTest
//                .getInstantExportBeanFactoryTest()
//                .getDataExportRequestMapperInitializer()
//                .getValidRequestDataForEntireDataInCollection();
//
//        Mono<DatabaseCredential> databaseCredentialDetails = exportServiceBeanFactory
//                .getDatabaseBeanFactory()
//                .getDatabaseCredentialService()
//                .getDatabaseCredentialDetails(dataExportRequestMapper);
//
//        StepVerifier.create(databaseCredentialDetails)
//                .expectSubscription()
//                .consumeNextWith(databaseCredentialDetail -> isDatabaseCredentialDetailsValid(databaseCredentialDetail))
//                .verifyComplete();
//    }

    @Test
    public void setDatabaseConnectionClientInStore_InvalidDatabaseConnectionClient_PortNotPresent(){
        environmentVariables.set("DATABASE_USERNAME_LOCAL", "bishal");
        environmentVariables.set("DATABASE_PASSWORD_LOCAL", "bishal");
        environmentVariables.set("DATABASE_HOST_URL_LOCAL", "localhost");
        final DataExportRequestMapper dataExportRequestMapper = exportServiceBeanFactoryTest
                .getInstantExportBeanFactoryTest()
                .getDataExportRequestMapperInitializer()
                .getValidRequestDataForEntireDataInCollection();

        Mono<DatabaseCredential> databaseCredentialDetails = exportServiceBeanFactory
                .getDatabaseBeanFactory()
                .getDatabaseCredentialService()
                .getDatabaseCredentialDetails(dataExportRequestMapper);

        StepVerifier.create(databaseCredentialDetails)
                .expectSubscription()
                .expectError()
                .verify();
    }
    @Test
    public void setDatabaseConnectionClientInStore_InvalidDatabaseConnectionClient_UsernameEnvironmentVariableNotPresent(){
        environmentVariables.set("DATABASE_PORT_LOCAL", "27017");
        environmentVariables.set("DATABASE_PASSWORD_LOCAL", "bishal");
        environmentVariables.set("DATABASE_HOST_URL_LOCAL", "localhost");
        final DataExportRequestMapper dataExportRequestMapper = exportServiceBeanFactoryTest
                .getInstantExportBeanFactoryTest()
                .getDataExportRequestMapperInitializer()
                .getValidRequestDataForEntireDataInCollection();

        Mono<DatabaseCredential> databaseCredentialDetails = exportServiceBeanFactory
                .getDatabaseBeanFactory()
                .getDatabaseCredentialService()
                .getDatabaseCredentialDetails(dataExportRequestMapper);

        StepVerifier.create(databaseCredentialDetails)
                .expectSubscription()
                .expectError()
                .verify();
    }
    @Test
    public void setDatabaseConnectionClientInStore_InvalidDatabaseConnectionClient_PasswordEnvironmentVariableNotPresent(){
        environmentVariables.set("DATABASE_PORT_LOCAL", "27017");
        environmentVariables.set("DATABASE_USERNAME_LOCAL", "bishal");
        environmentVariables.set("DATABASE_HOST_URL_LOCAL", "localhost");
        final DataExportRequestMapper dataExportRequestMapper = exportServiceBeanFactoryTest
                .getInstantExportBeanFactoryTest()
                .getDataExportRequestMapperInitializer()
                .getValidRequestDataForEntireDataInCollection();

        Mono<DatabaseCredential> databaseCredentialDetails = exportServiceBeanFactory
                .getDatabaseBeanFactory()
                .getDatabaseCredentialService()
                .getDatabaseCredentialDetails(dataExportRequestMapper);

        StepVerifier.create(databaseCredentialDetails)
                .expectSubscription()
                .expectError()
                .verify();
    }
    @Test
    public void setDatabaseConnectionClientInStore_InvalidDatabaseConnectionClient_HostUrlEnvironmentVariableNotPresent(){
        environmentVariables.set("DATABASE_PORT_LOCAL", "27017");
        environmentVariables.set("DATABASE_USERNAME_LOCAL", "bishal");
        environmentVariables.set("DATABASE_PASSWORD_LOCAL", "bishal");
        final DataExportRequestMapper dataExportRequestMapper = exportServiceBeanFactoryTest
                .getInstantExportBeanFactoryTest()
                .getDataExportRequestMapperInitializer()
                .getValidRequestDataForEntireDataInCollection();

        Mono<DatabaseCredential> databaseCredentialDetails = exportServiceBeanFactory
                .getDatabaseBeanFactory()
                .getDatabaseCredentialService()
                .getDatabaseCredentialDetails(dataExportRequestMapper);

        StepVerifier.create(databaseCredentialDetails)
                .expectSubscription()
                .expectError()
                .verify();
    }

    private void isDatabaseCredentialDetailsValid(final DatabaseCredential databaseCredential) {
        Assert.assertNotNull(databaseCredential);
        Assert.assertNotNull(databaseCredential.getHostUrl());
        Assert.assertNotNull(databaseCredential.getPortNumber());
        Assert.assertNotNull(databaseCredential.getUsername());
        Assert.assertNotNull(databaseCredential.getPassword());
    }



}
