package com.bishal.bulk.export.database.service.impl;


import com.bishal.bulk.export.common.mapper.resquest.DataExportRequestMapper;
import com.bishal.bulk.export.common.service.IExportServiceBeanFactory;
import com.bishal.bulk.export.common.service.IExportServiceBeanFactoryTest;
import com.bishal.bulk.export.common.utils.database.DatabaseConnectionStoreUtils;
import com.bishal.bulk.export.database.service.IDatabaseBeanFactory;
import com.bishal.bulk.export.database.service.IDatabaseCredentialService;
import com.bishal.bulk.export.database.utils.DatabaseCredentialUtils;
import org.junit.*;
import org.junit.contrib.java.lang.system.EnvironmentVariables;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import javax.validation.Valid;

@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@EnableAutoConfiguration
@ActiveProfiles("test")
@PrepareForTest(DatabaseCredentialUtils.class)
public class TestDatabaseConnectionServiceImpl {

    @Mock
    private IExportServiceBeanFactory exportServiceBeanFactory;

    @Autowired
    private IExportServiceBeanFactoryTest exportServiceBeanFactoryTest;

    @Mock
    private DataExportRequestMapper dataExportRequestMapper;

    @Mock
    private IDatabaseBeanFactory databaseBeanFactory;

    @Mock
    private IDatabaseCredentialService databaseCredentialService;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void setDatabaseConnectionClientInStore_ValidDatabaseConnectionClient_SuccessfullyStoredDatabaseConnectionClient(){
//        environmentVariables.set("DATABASE_PORT_LOCAL", "27017");
//        environmentVariables.set("DATABASE_USERNAME_LOCAL", "bishal");
//        environmentVariables.set("DATABASE_PASSWORD_LOCAL", "bishal");
//        environmentVariables.set("DATABASE_HOST_URL_LOCAL", "localhost");
//        final DataExportRequestMapper dataExportRequestMapper = exportServiceBeanFactoryTest
//                .getInstantExportBeanFactoryTest()
//                .getDataExportRequestMapperInitializer()
//                .getValidRequestDataForEntireDataInCollection();

        Mockito.when(exportServiceBeanFactory.getDatabaseBeanFactory()).thenReturn(databaseBeanFactory);
        Mockito.when(exportServiceBeanFactory.getDatabaseBeanFactory().getDatabaseCredentialService()).thenReturn(databaseCredentialService);
            Mockito.when(exportServiceBeanFactory
                    .getDatabaseBeanFactory()
                    .getDatabaseCredentialService()
                    .getDatabaseCredentialDetails(dataExportRequestMapper))
                    .thenReturn(exportServiceBeanFactoryTest
                            .getDatabaseBeanFactoryTest()
                            .getDatabaseCredentialInitializerService()
                            .getValidDatabaseCredentials());


        Mono<ReactiveMongoTemplate> databaseConnectionClientStoreResponse = exportServiceBeanFactory
                .getDatabaseBeanFactory()
                .getDatabaseCredentialService()
                .getDatabaseCredentialDetails(dataExportRequestMapper)
                .map( aDatabaseCredential -> DatabaseConnectionStoreUtils.getDatabaseConnectionFromDatabaseStore(aDatabaseCredential, dataExportRequestMapper.getDatabaseUniqueKey()));

        StepVerifier.create(databaseConnectionClientStoreResponse)
                .expectSubscription()
                .consumeNextWith(reactiveMongoTemplate -> Assert.assertNotNull(reactiveMongoTemplate))
                .verifyComplete();
    }
}
